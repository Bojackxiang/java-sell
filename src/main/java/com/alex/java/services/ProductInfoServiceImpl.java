package com.alex.java.services;

import com.alex.java.dataObject.ProductInfo;
import com.alex.java.enums.OrderStatusEnum;
import com.alex.java.enums.ResultEnum;
import com.alex.java.exception.OrderException;
import com.alex.java.repo.ProductInfoRepo;
import java.util.Optional;
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

    @Override
    public void increaseStockingByOne(String productId) {
        // working
    }

    /*
     * 测试一下这边的transactional 是否会起作用
     */
    @Override
    public void decreaseStockingByOne(String productId) {
        ProductInfo foundResult = productInfoRepo
            .findProductInfosByProductId(productId)
            .isPresent()
            ? productInfoRepo.findProductInfosByProductId(productId).get()
            : null;

        if (foundResult == null){
            throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        foundResult.setProductStock(foundResult.getProductStock() - 1);
        // working
    }


}
