package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrdenRequestDTO;
import com.pragma.powerup.application.dto.response.OrdenResponseDTO;

import java.util.List;

public interface IOrdenHandler {

    void saveOrden(OrdenRequestDTO ordenRequest);

    List<OrdenResponseDTO> getAllOrdenesWithPagination(Integer page, Integer size, String estado, String idEmpleado);


    void takeOrdenAndUpdateStatus(Long idOrden, String estado, String idEmpleado);

    void updateAndNotifyOrdenReady(Long idOrden, String idEmpleado);


    void deliverOrden(Long idOrden, String pin, String idEmpleado);

    // void cancelOrden(Long idOrden);
}
