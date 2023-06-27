package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrdenServicePort;
import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.domain.model.Ordenes.OrdenPlatoRequest;
import com.pragma.powerup.domain.model.Ordenes.OrdenPlatoResponse;
import com.pragma.powerup.domain.model.Ordenes.OrdenRequest;
import com.pragma.powerup.domain.model.Ordenes.OrdenResponse;
import com.pragma.powerup.domain.spi.persistence.IOrdenPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.PlatoNoExisteException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenUseCase implements IOrdenServicePort {

        private final IOrdenPersistencePort ordenPersistencePort;


        private final IRestaurantPersistencePort restaurantPersistencePort;

        private final IPlatoPersistencePort platoPersistencePort;

        private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

        public OrdenUseCase(IOrdenPersistencePort ordenPersistencePort,  IRestaurantPersistencePort restaurantPersistencePort, IPlatoPersistencePort platoPersistencePort, IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort) {
            this.ordenPersistencePort = ordenPersistencePort;
            this.restaurantPersistencePort = restaurantPersistencePort;
            this.platoPersistencePort = platoPersistencePort;
           this.restaurantEmployeePersistencePort = restaurantEmployeePersistencePort;
        }

        @Override
        public void saveOrden(OrdenRequest ordenRequest) {
            Date date = new Date();

            List<String> estados = List.of("PENDIENTE", "EN_PREPARACION", "LISTO");

            Long idRestaurante = ordenRequest.getResturanteId();

            Restaurant restaurantModel= restaurantPersistencePort.getRestaurantById(idRestaurante);
            if(restaurantModel==null) throw new RestaurantNotExistException();
            Orden ordenModel = new Orden(-1L, null,date,"PENDIENTE",null,restaurantModel);

            List<OrdenPlatoRequest> orderDishes = ordenRequest.getPlatos();
            if(orderDishes.isEmpty()){
                throw new NoDataFoundException();
            }
            for (int i=0; i<orderDishes.size();i++) {
                Plato plato = platoPersistencePort.getPlatoById(orderDishes.get(i).getIdPlatos());
                if (plato == null) throw new PlatoNoExisteException();
                if (plato.getRestaurantId().getId() != ordenModel.getRestaurante().getId()) throw new PlatoRestaurantIdNotIsEqualsOrderException();
                if(!plato.getActivo()) throw new PlatoIsInactiveException();
            }
            Orden order =ordenPersistencePort.saveOrden(ordenModel);


            List<OrdenPlato> ordenPlatoEmpty = new ArrayList<>();
            for (int i=0; i<orderDishes.size();i++){
                Plato dishModel= platoPersistencePort.getPlatoById(orderDishes.get(i).getIdPlatos());
                OrdenPlato OrdenPlato = new OrdenPlato(-1L, order,dishModel, String.valueOf(orderDishes.get(i).getCantidad()));
                ordenPlatoEmpty.add(OrdenPlato);
            }

            ordenPersistencePort.saveOrdenPlato(ordenPlatoEmpty);
        }

        @Override
        public List<OrdenResponse> getAllOrdersWithPagination(Integer page, Integer size, String estado, String idEmpleado) {

            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(String.valueOf(idEmpleado));

            List<OrdenResponse> listaPedidosResponse = new ArrayList<>();
            Long restauranteId = Long.parseLong(restaurantEmployeeModel.getRestaurantId());
            List<Orden> pedidos = ordenPersistencePort.getAllOrdenesWithPagination(page, size,restauranteId ,estado);

            for (int i=0; i<pedidos.size();i++){
                OrdenResponse orderResponseModel = new OrdenResponse();
                orderResponseModel.setId(pedidos.get(i).getId());
                orderResponseModel.setIdCliente(pedidos.get(i).getIdCliente());
                if(pedidos.get(i).getChef()==null) orderResponseModel.setIdChef(null);
                else orderResponseModel.setIdChef(pedidos.get(i).getChef().getId());
                orderResponseModel.setFecha(pedidos.get(i).getFecha());
                orderResponseModel.setPedidoPlatos(new ArrayList<>());

                List<OrdenPlato> pedidoPlatos = ordenPersistencePort.getAllOrdenessByPedido(pedidos.get(i).getId());
                for (int k=0; k<pedidoPlatos.size(); k++){
                    Plato plato= platoPersistencePort.getPlatoById(pedidoPlatos.get(k).getPlato().getId());
                    OrdenPlatoResponse OrdenPlatoResponse = new OrdenPlatoResponse();
                    OrdenPlatoResponse.setId(plato.getId());
                    OrdenPlatoResponse.setNombre(plato.getNombre());
                    OrdenPlatoResponse.setPrecio(plato.getPrecio());
                    OrdenPlatoResponse.setDescripcion(plato.getDescripcion());
                    OrdenPlatoResponse.setUrlImagen(plato.getUrlImagen());
                    OrdenPlatoResponse.setCategoriaId(plato.getCategoriaId());
                    OrdenPlatoResponse.setCantidad(pedidoPlatos.get(k).getCantidad());

                    orderResponseModel.getPedidoPlatos().add(OrdenPlatoResponse);
                }
                listaPedidosResponse.add(orderResponseModel);
            }
            return listaPedidosResponse;
        }

        @Override
        public void takeOrderAndUpdateStatus(Long idOrder, String estado, String idEmpleado) {
            if(!estado.equals("EN_PREPARACION")) throw new NoDataFoundException();
            if(Boolean.FALSE.equals(ordenPersistencePort.existsByIdAndEstado(idOrder, "PENDIENTE"))) throw new NoDataFoundException();
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(idEmpleado);
            if(restaurantEmployeeModel==null) throw new RestaurantEmployeeNotExistException();
            Orden orderModel= ordenPersistencePort.getOrdenById(idOrder);
            if(orderModel==null) throw new OrderNotExistException();

            Long idRestaurantEmployeeAuth = Long.valueOf(restaurantEmployeeModel.getRestaurantId());
            Long idRestaurantOrder = orderModel.getRestaurante().getId();

            if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new OrderRestaurantMustBeEqualsEmployeeRestaurantException();

            orderModel.setChef(restaurantEmployeeModel);
            orderModel.setEstado(estado);

            ordenPersistencePort.saveOrden(orderModel);
        }

        @Override
        public void updateAndNotifyOrdenReady(Long idOrden, String idEmpleado) {
            if(Boolean.FALSE.equals(ordenPersistencePort.existsByIdAndEstado(idOrden, "EN_PREPARACION"))) throw new NoDataFoundException();
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(idEmpleado);
            if(restaurantEmployeeModel==null) throw new RestaurantEmployeeNotExistException();
            Orden orden= ordenPersistencePort.getOrdenById(idOrden);
            if(orden==null) throw new OrderNotExistException();

            Long idRestaurantEmployeeAuth = Long.valueOf(restaurantEmployeeModel.getRestaurantId());
            Long idRestaurantOrder = orden.getRestaurante().getId();

            if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new OrderRestaurantMustBeEqualsEmployeeRestaurantException();

            orden.setEstado("LISTO");
            ordenPersistencePort.saveOrden(orden);
        }

        @Override
        public void deliverOrder(Long idOrden, String pin, String idEmpleado) {
            if(Boolean.FALSE.equals(ordenPersistencePort.existsByIdAndEstado(idOrden, "LISTO"))) throw new NoDataFoundException();
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(idEmpleado);
            if(restaurantEmployeeModel==null) throw new RestaurantEmployeeNotExistException();
            Orden orden= ordenPersistencePort.getOrdenById(idOrden);
            if(orden==null) throw new OrderNotExistException();

            Long idRestaurantOrder = orden.getRestaurante().getId();


            orden.setEstado("ENTREGADO");
            ordenPersistencePort.saveOrden(orden);
        }
}
