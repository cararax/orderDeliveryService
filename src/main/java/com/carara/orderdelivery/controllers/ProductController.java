package com.carara.orderdelivery.controllers;

import com.carara.orderdelivery.dtos.ProductDto;
import com.carara.orderdelivery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    private ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }
}
