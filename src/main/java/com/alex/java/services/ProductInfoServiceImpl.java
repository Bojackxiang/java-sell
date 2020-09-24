package com.alex.java.services;

import com.alex.java.dataObject.ProductInfo;
import com.alex.java.repo.ProductInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceImpl implements ProductInfoServiceInterface {

    @Autowired
    ProductInfoRepo productInfoRepo;


    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepo.findAll();
    }
}
