package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Ordenes.OrdenRequest;
import com.pragma.powerup.domain.model.Ordenes.OrdenResponse;

import java.util.List;

public interface IOrdenServicePort {

    void saveOrden(OrdenRequest ordenRequest);

    List<OrdenResponse> getAllOrdersWithPagination(Integer page, Integer size, String estado, String idEmpleado);

    void takeOrderAndUpdateStatus(Long idOrder, String estado, String idEmpleado);


    void updateAndNotifyOrdenReady(Long idOrder, String idEmpleado);

    void deliverOrder(Long idOrder, String pin, String idEmpleado);

    //void cancelOrden(Long idOrder);

}
