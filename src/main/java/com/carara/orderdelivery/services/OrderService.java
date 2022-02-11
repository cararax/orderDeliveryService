package com.carara.orderdelivery.services;

import com.carara.orderdelivery.dtos.OrderDto;
import com.carara.orderdelivery.dtos.ProductDto;
import com.carara.orderdelivery.entities.Order;
import com.carara.orderdelivery.entities.OrderStatus;
import com.carara.orderdelivery.entities.Product;
import com.carara.orderdelivery.repositories.OrderRepository;
import com.carara.orderdelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public List<OrderDto> findAll() {
        List<Order> orderList = orderRepository.findByStatusIsOrderByMomentAsc(OrderStatus.PENDING);
        return orderList.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderDto insert(OrderDto orderDto) {
        Order order = new Order(null, orderDto.getAddress(), orderDto.getLatitude(), orderDto.getLongitude(),
                Instant.now(), OrderStatus.PENDING);

        for (ProductDto productDto : orderDto.getProducts()) {
            Optional<Product> product = productRepository.findById(productDto.getId());
            if (product.isPresent())
                order.getProducts().add(product.get());
        }

        order = orderRepository.save(order);

        return new OrderDto(order);
    }

    @Transactional
    public OrderDto setDelivered(Long id) {
        Order order = orderRepository.findById(id).get();
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDto(order);


    }
}
