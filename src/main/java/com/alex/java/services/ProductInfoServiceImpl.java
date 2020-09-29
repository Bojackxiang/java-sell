package com.alex.java.services;

import com.alex.java.dataObject.ProductInfo;
import com.alex.java.repo.ProductInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoServiceInterface {

    @Autowired
    ProductInfoRepo productInfoRepo;


    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepo.save(productInfo);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepo.findAll();
    }

    @Override
    public ProductInfo findOneProductInfoById(String id) {
        ProductInfo foundProductInfo =
                productInfoRepo.findProductInfosByProductId(id).orElse(null);

        return foundProductInfo;

    }

    @Override
    public Page<ProductInfo> findProductInfoByPage(Pageable pageable) {
        return productInfoRepo.findAll(pageable);
        /*
         * Find返回的可以是多样的，
         */
    }

    @Override
    public List<ProductInfo> findProductByStatus(Integer status) {
        return productInfoRepo.findByProductStatus(status);
    }


}
