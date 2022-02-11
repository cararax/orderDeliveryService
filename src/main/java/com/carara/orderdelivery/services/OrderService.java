package com.carara.orderdelivery.services;

import com.carara.orderdelivery.dtos.OrderDto;
import com.carara.orderdelivery.entities.Order;
import com.carara.orderdelivery.entities.OrderStatus;
import com.carara.orderdelivery.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public List<OrderDto> findAll() {
        List<Order> orderList = orderRepository.findByStatusIsOrderByMomentAsc(OrderStatus.PENDING);
        return orderList.stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
