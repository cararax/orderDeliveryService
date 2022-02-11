package com.carara.orderdelivery.controllers;

import com.carara.orderdelivery.dtos.OrderDto;
import com.carara.orderdelivery.dtos.ProductDto;
import com.carara.orderdelivery.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all products",
            description = "Get all products ordered by product name",
            tags = {"Product"})

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})

    private ResponseEntity<List<ProductDto>> findAllByOrderByName() {
        List<ProductDto> productList = productService.findAllByOrderByName();
        return ResponseEntity.ok().body(productList);
    }
}
