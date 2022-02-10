package com.carara.orderdelivery.services;

import com.carara.orderdelivery.dtos.ProductDto;
import com.carara.orderdelivery.entities.Product;
import com.carara.orderdelivery.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDto> findAllByOrderByName() {
        List<Product> productList = productRepository.findAllByOrderByNameAsc();
        return productList.stream().map(ProductDto::new).collect(Collectors.toList());
    }

}
