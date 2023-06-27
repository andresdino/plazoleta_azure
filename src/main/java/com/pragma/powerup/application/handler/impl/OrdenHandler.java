package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrdenRequestDTO;
import com.pragma.powerup.application.dto.response.OrdenResponseDTO;
import com.pragma.powerup.application.handler.IOrdenHandler;
import com.pragma.powerup.application.mapper.request.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.response.IOrderResponseMapper;
import com.pragma.powerup.domain.api.IOrdenServicePort;
import com.pragma.powerup.domain.model.Ordenes.OrdenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdenHandler implements IOrdenHandler {


    private final IOrdenServicePort ordenServicePort;

    private final IOrderRequestMapper ordenRequestMapper;


    private final IOrderResponseMapper ordenResponseMapper;


    @Override
    public void saveOrden(OrdenRequestDTO ordenRequest) {
        OrdenRequest orderRequestModel= ordenRequestMapper.toOrdenRequest(ordenRequest);
        ordenServicePort.saveOrden(orderRequestModel);
    }

    @Override
    public List<OrdenResponseDTO> getAllOrdenesWithPagination(Integer page, Integer size, String estado, String idEmpleado) {
        return ordenResponseMapper.toOrdenResponseList(ordenServicePort.getAllOrdersWithPagination(page,size,estado, idEmpleado));
    }

    @Override
    public void takeOrdenAndUpdateStatus(Long idOrden, String estado, String idEmpleado) {
        ordenServicePort.takeOrderAndUpdateStatus(idOrden,estado , idEmpleado);
    }

    @Override
    public void updateAndNotifyOrdenReady(Long idOrden, String idEmpleado){
        ordenServicePort.updateAndNotifyOrdenReady(idOrden, idEmpleado);
    }

    @Override
    public void deliverOrden(Long idOrden, String pin, String idEmpleado) {
        ordenServicePort.deliverOrder(idOrden,pin, idEmpleado);
    }

    /*@Override
    public void cancelOrden(Long idOrden) {
        ordenServicePort.cancelOrden(idOrden);

    }*/
}
