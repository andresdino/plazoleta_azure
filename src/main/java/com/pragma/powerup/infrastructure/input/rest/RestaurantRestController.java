package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantRequestDTO;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDTO;
import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurant) {
        restaurantHandler.saveRestaurant(restaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All restaurants returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @Operation(summary = "Get all restaurants with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All restaurants returned paginated",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantPaginationResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<RestaurantPaginationResponseDTO>> getAllRestaurantsPagination(@PathVariable(value = "page" )Integer page, @PathVariable(value = "size") Integer size) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantsWithPagination(page,size));
    }

    @Operation(summary = "Get restaurant by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestaurantResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Restaurant no found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable(value = "id") Long restaurantId) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantById(restaurantId));
    }


    @Operation(summary = "Get restaurant by Id_propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    })
    @GetMapping("/restaurantByIdPropietario/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantByIdPropietario(@PathVariable(value = "id") Long idPropietario) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantByIdPropietario(idPropietario));
    }

    @Operation(summary = "Detele a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deteteRestaurantById(@PathVariable(value = "id") Long restaurantId) {
        restaurantHandler.deleteRestaurantById(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
