package com.hyc.service;

import com.hyc.dao.ProductMapper;
import com.hyc.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public Product save(Product product) {
        productMapper.insertSelective(product);
        return product;
    }
}
