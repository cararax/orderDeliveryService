package com.carara.orderdelivery.controllers;

import com.carara.orderdelivery.dtos.OrderDto;
import com.carara.orderdelivery.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders",
            description = "Get all orders with PENDING status sorted by ASC creation time",
            tags = {"Order"})

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<List<OrderDto>> findAll() {
        List<OrderDto> orderLIst = orderService.findAll();
        return ResponseEntity.ok().body(orderLIst);
    }


    @PostMapping
    @Operation(summary = "Insert a order.",
            description = "Insert a new order. Default status is pending and default moment is now.",
            tags = {"Order"})

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success, new order inserted.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto orderDto) {
        orderDto = orderService.insert(orderDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(("/{id}"))
                .buildAndExpand(orderDto.getId()).toUri();

        return ResponseEntity.created(uri).body(orderDto);
    }

    @PutMapping("/{id}/delivered")
    @Operation(summary = "Update order status.",
            description = "Update order status by changing status from PENDING to DELIVERED.",
            tags = {"Order"})

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success, status updated to DELIVERED.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found.")})
    public ResponseEntity<Object> setOrderDelivered(@PathVariable Long id) {
        try {
            OrderDto orderDto = orderService.setDelivered(id);
            return ResponseEntity.ok().body(orderDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }
}
