package com.carara.orderdelivery.controllers;

import com.carara.orderdelivery.dtos.OrderDto;
import com.carara.orderdelivery.services.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
