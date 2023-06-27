package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrdenRequestDTO;
import com.pragma.powerup.application.dto.response.OrdenResponseDTO;
import com.pragma.powerup.application.handler.IOrdenHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor

public class OrdenRestController {


    private final IOrdenHandler ordenHandler;



    @Operation(summary = "Add a new orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/placeAnOrden")
    public ResponseEntity<OrdenResponseDTO> placeAnOrden(@Validated @RequestBody OrdenRequestDTO ordenRequest) {
        ordenHandler.saveOrden(ordenRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get Orden By State")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Orders found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orders don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/getOrdersByStatePaginated/page/{page}/size/{size}/status/{estado}/idEmpleado/{idEmpleado}")
    public ResponseEntity<List<OrdenResponseDTO>> getAllOrdenByState(@PathVariable Integer page, @PathVariable Integer size, @PathVariable(value = "estado" ) String estado, @PathVariable String idEmpleado) {
        if (size <= 0L || page < 0L || estado.isBlank() || estado.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(ordenHandler.getAllOrdenesWithPagination(page, size, estado,idEmpleado));
    }

 /*   @Operation(summary = "Take order and update status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order taken", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/takeOrderAndUpdateStatus/{idOrder}/status/{status}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<Void> takeOrdenAndUpdateStatus(@PathVariable Long idOrder, @PathVariable String status) {
        if (idOrder <= 0L || status.isBlank() || status.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        ordenHandler.takeOrdenAndUpdateStatus(idOrder, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @Operation(summary = "Orden ready")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order ready", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/updateAndNotifyOrdenReady/{idOrder}/idEmpleado/{idEmpleado}")
    public ResponseEntity<Void> updateAndNotifyOrderReady(@PathVariable Long idOrder, @PathVariable String idEmpleado) {
        if (idOrder <= 0L) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ordenHandler.updateAndNotifyOrdenReady(idOrder , idEmpleado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Orden deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/ordenDeliver/{idOrden}/pin/{pin}/idEmpleado/{idEmpleado}")
    public ResponseEntity<Void> ordenDeliver(@PathVariable Long idOrden, @PathVariable String pin, @PathVariable String idEmpleado) {
        if (idOrden <= 0L) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        ordenHandler.deliverOrden(idOrden, pin, idEmpleado);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*@Operation(summary = "Cancel Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order cancel", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })

    @PutMapping("/cancelOrder/{idOrder}")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long idOrder){
        if(idOrder <= 0L)  return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        ordenHandler.cancelOrden(idOrder);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/


}
