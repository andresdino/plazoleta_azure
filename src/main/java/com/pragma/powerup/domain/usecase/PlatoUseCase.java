package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IPlatoServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.exception.OwnerAuthMustBeOwnerRestaurantException;
import com.pragma.powerup.domain.exception.OwnerNotAuthenticatedException;
import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.domain.spi.persistence.ICategoriaPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.PlatoNoExisteException;

import java.util.ArrayList;
import java.util.List;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    private final IRestaurantPersistencePort restaurantPersistencePort;

    private final ICategoriaPersistencePort categoriaPersistencePort;

 //   private  final IToken token;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoriaPersistencePort categoriaPersistencePort/*, IToken token*/) {
        this.platoPersistencePort = platoPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoriaPersistencePort = categoriaPersistencePort;
  //      this.token = token;
    }


    @Override
    public void savePlato(Plato plato) {

        if(restaurantPersistencePort.getRestaurantById(plato.getRestaurantId().getId()) == null) {throw new DomainException("Restaurante no Existe");
        }
        if(categoriaPersistencePort.getCategoriaById(plato.getCategoriaId().getId()) == null){ throw new DomainException("Categoria no Exsite");}


  //      validateOwnerAuthWithOwnerRestaurant(plato);

        plato.setActivo(true);

        platoPersistencePort.savePlato(plato);

    }

    @Override
    public Plato getPlatoById(Long id) {
        return platoPersistencePort.getPlatoById(id);
    }

    @Override
    public List<Plato> getAllPlatos() {
        return platoPersistencePort.getAllPlato();
    }

    @Override
    public void deletePlatoById(Long id) {
        platoPersistencePort.deletePlatoById(id);
    }

    @Override
    public short putPlato(Long id, Plato plato) {
        Plato platoPut = platoPersistencePort.getPlatoById(id);
        if(platoPut==null) {
            throw new PlatoNoExisteException();
        }
   //     validateOwnerAuthWithOwnerRestaurant(platoPut);

        platoPut.setPrecio(plato.getPrecio());
        platoPut.setDescripcion(plato.getDescripcion());

        platoPersistencePort.savePlato(platoPut);
        return 0;
    }

    @Override
    public void putEnableDisablePlato(Long platoId, Long flag) {
        Plato platoPut = platoPersistencePort.getPlatoById(platoId);
        if(platoPut==null) {
            throw new PlatoNoExisteException();
        }

 //       validateOwnerAuthWithOwnerRestaurant(platoPut);

        boolean isEnableOrDisable = (flag==1)?true:false;
        platoPut.setActivo(isEnableOrDisable);

        platoPersistencePort.savePlato(platoPut);
    }

    /*private void validateOwnerAuthWithOwnerRestaurant(Plato plato){
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new OwnerNotAuthenticatedException();
        Long idOwnerAuth = token.getUsuarioAutenticadoId(bearerToken);
        Long idOwnerRestaurant =  restaurantPersistencePort.getRestaurantById(plato.getRestaurantId().getId()).getIdPropietario();
        if(idOwnerAuth!=idOwnerRestaurant) throw new OwnerAuthMustBeOwnerRestaurantException();

    }*/



    @Override
    public List<Plato> findAllByRestauranteId(Long idRestaurante, Integer page, Integer size) {
        List<Plato> platoModelList=platoPersistencePort.findAllByRestauranteId(idRestaurante, page,size);
        List<Plato> platosActivos=new ArrayList<>();
        for (Plato plato:platoModelList) {
            if(plato.getActivo()){
                platosActivos.add(plato);
            }
        }
        return platosActivos;
    }

}
