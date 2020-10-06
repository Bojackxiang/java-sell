package com.alex.java.controllers;

import com.alex.java.Utils.DataUtils.Datautils;
import com.alex.java.Utils.ProductDataObj;
import com.alex.java.Utils.ProductInfoObj;
import com.alex.java.Utils.ResponseUtils;
import com.alex.java.Utils.ResponseObj;
import com.alex.java.dataObject.ProductCategory;
import com.alex.java.dataObject.ProductInfo;
import com.alex.java.services.CategoryServiceImpl;
import com.alex.java.services.ProductInfoServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  @Autowired
  ProductInfoServiceImpl productInfoService;

  @Autowired
  CategoryServiceImpl categoryService;

  @GetMapping("/list")
  public ResponseObj<Object> list() {
    // 用于查询所有的山家的商品，product status 是 1的所有的商品
    /* NOTE:
     * 这里需要注意，，java的范型一定要声明是什么样子的范型
     */

    List<ProductInfo> availableProducts = productInfoService.findProductByStatus(1);

    List<Integer> categoryTypeList = new ArrayList<>();

    for(ProductInfo product: availableProducts){
      Integer productType = product.getCategoryType();
      categoryTypeList.add(productType);
    }

    List<ProductCategory> availableProductCategories = categoryService.findCategoryTypeIn(
        categoryTypeList
    );

    List<ProductDataObj> responseDataList = new ArrayList<>();

    for (ProductCategory productCategory : availableProductCategories) {
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

    return ResponseUtils.successResponse(responseDataList);
  }
}
