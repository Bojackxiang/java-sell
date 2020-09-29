package com.alex.java.controllers;

import com.alex.java.Utils.ProductDataObj;
import com.alex.java.Utils.ProductInfoObj;
import com.alex.java.Utils.ResponseObj;
import com.alex.java.dataObject.ProductCategory;
import com.alex.java.dataObject.ProductInfo;
import com.alex.java.repo.ProductInfoRepo;
import com.alex.java.services.CategoryServiceImpl;
import com.alex.java.services.ProductInfoServiceImpl;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  @Autowired
  ProductInfoServiceImpl productInfoService;

  @Autowired
  CategoryServiceImpl categoryService;

  @GetMapping("/list")
  public ResponseObj<List<ProductDataObj>> list() {
    // 用于查询所有的山家的商品，product status 是 1的所有的商品
    /* NOTE:
     * 这里需要注意，，java的范型一定要声明是什么样子的范型
     *
     */

    ResponseObj<List<ProductDataObj>> responseObj = new ResponseObj<>();
    responseObj.setCode(1);
    responseObj.setMessage("this is a message");


    // 获取所有 available 的商品
    List<ProductInfo> availableProducts = productInfoService.findProductByStatus(1);

    System.out.println(availableProducts);

    // 获取所有上架商品的类别
//    List<Integer> categoryTypeList = availableProducts
//        .stream()
//        .map(ProductInfo::getCategoryType)
//        .collect(Collectors.toList());
    List<Integer> categoryTypeList = new ArrayList<>();
    for(ProductInfo product: availableProducts){
      Integer productType = product.getCategoryType();
      categoryTypeList.add(productType);
    }


    System.out.println(categoryTypeList);// 问题，如果有重复的怎么办

    // 获取所有 available 商品的 categories
    List<ProductCategory> availableProductCategories = categoryService.findCategoryTypeIn(
        categoryTypeList
    );

    List<ProductDataObj> responseDataList = new ArrayList<>();

    for (ProductCategory productCategory : availableProductCategories) {
      // 这边是在拼装 response 中的 data 的结构
      ProductDataObj productDataObj = new ProductDataObj();
      Integer productCategoryType = productCategory.getCategoryType();
      String productCategoryName = productCategory.getCategoryName();
      productDataObj.setCategoryTypes(productCategoryType);
      productDataObj.setCategoryName(productCategoryName);

      List<ProductInfoObj> productInfoList = new ArrayList<>();
      for (ProductInfo productInfo : availableProducts) {
        if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
          ProductInfoObj availableProductInfo = new ProductInfoObj();
          BeanUtils.copyProperties(productInfo, availableProductInfo);
          productInfoList.add(availableProductInfo);
        }
      }
      productDataObj.setProductInfoList(productInfoList);

      responseDataList.add(productDataObj);
    }

    System.out.println(responseDataList);

    responseObj.setData(responseDataList);

    return responseObj;
  }
}
