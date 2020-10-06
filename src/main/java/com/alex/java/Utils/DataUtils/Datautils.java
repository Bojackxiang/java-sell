package com.alex.java.Utils.DataUtils;

import com.alex.java.Utils.ProductDataObj;
import com.alex.java.Utils.ProductInfoObj;
import com.alex.java.dataObject.ProductCategory;
import com.alex.java.dataObject.ProductInfo;
import com.alex.java.services.CategoryServiceImpl;
import com.alex.java.services.ProductInfoServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Datautils {

    @Autowired
    static ProductInfoServiceImpl productInfoService;

    @Autowired
    static CategoryServiceImpl categoryService;

    public static List<ProductDataObj> generateSellList() {
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

        return responseDataList;
    }
}
