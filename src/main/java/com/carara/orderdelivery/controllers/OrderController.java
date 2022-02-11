package com.carara.orderdelivery.controllers;

import com.carara.orderdelivery.dtos.OrderDto;
import com.carara.orderdelivery.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<OrderDto>> findAll() {
        List<OrderDto> orderLIst = orderService.findAll();
        return ResponseEntity.ok().body(orderLIst);
    }

    @PostMapping
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto orderDto) {
        orderDto = orderService.insert(orderDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(("/{id}"))
                .buildAndExpand(orderDto.getId()).toUri();

        return ResponseEntity.created(uri).body(orderDto);
    }
}
