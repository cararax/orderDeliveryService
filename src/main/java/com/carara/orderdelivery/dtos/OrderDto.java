package com.carara.orderdelivery.dtos;

import com.carara.orderdelivery.entities.Order;
import com.carara.orderdelivery.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Instant moment;
    private OrderStatus status;

    private List<ProductDto> products = new ArrayList<>();

    public OrderDto(Order entity) {
        this.id = entity.getId();
        this.address = entity.getAddress();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.products = entity.getProducts().stream()
                .map(ProductDto::new).collect(Collectors.toList());
    }
}
