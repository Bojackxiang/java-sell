package com.alex.java.controllers;

import com.alex.java.Utils.ProductDataObj;
import com.alex.java.Utils.ProductInfo;
import com.alex.java.Utils.ResponseObj;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  @GetMapping("/list")
  public ResponseObj<ArrayList<ProductDataObj>> list() {
    /* NOTE:
     * 这里需要注意，，java的范型一定要声明是什么样子的范型
     *
     */

    ResponseObj<List<ProductDataObj>> responseObj = new ResponseObj<>();
    responseObj.setCode(1);
    responseObj.setMessage("this is a message");

    ProductDataObj productDataObj = new ProductDataObj();
    ProductInfo productInfo = new ProductInfo(); // foods

    productDataObj.setProductInfoList(Arrays.asList(productInfo, productInfo));


    // 开始为 response 中的 data 生成数据


    responseObj.setData(productDataObj.getProductInfoList());

    // 开始为 foot 生成数据 中的
    // productDataObjects.add(productInfo);


    return responseObj;
  }
}
