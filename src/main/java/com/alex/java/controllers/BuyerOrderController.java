package com.alex.java.controllers;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.ObjTransfer.OrderFormToOrderDTO;
import com.alex.java.Utils.ProductDataObj;
import com.alex.java.Utils.ResponseObj;
import com.alex.java.dataObject.OrderMaster;
import com.alex.java.enums.ResultEnum;
import com.alex.java.exception.OrderException;
import com.alex.java.form.OrderForm;
import com.alex.java.services.OrderServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {
  @Autowired
  OrderServiceImpl orderService;

  // 创建订单
  @PostMapping("/create")
  public Object createOrder(
      @Validated OrderForm orderForm,
      BindingResult bindingResult // 要穿件表单验证，就需要binding result
  ){
      // 处理validation
      if(bindingResult.hasErrors()){
        throw new OrderException(
            ResultEnum.FAILED_VALIDATION,
            bindingResult.getFieldError() == null
                ? "validation failed"
                : bindingResult.getFieldError().getDefaultMessage());
      }

      // 将 order form 转换为 orderDTO
      OrderDTO createdOrderDTO = OrderFormToOrderDTO
          .transferOrderFormToOrderDto(orderForm);

      // 检查传进来的 cart 是不是为空, 如果是空就停止
      if(CollectionUtils.isEmpty(createdOrderDTO.getOrderDetailList())){
        throw new OrderException(ResultEnum.NO_CARD);
      }

      // 调用创建订单的 service
      OrderMaster orderMaster =
          orderService.createOrder(createdOrderDTO);

      // 创建 返回的string
      Map<String, String> response = new HashMap<>();
      response.put("createId", orderMaster.getOrderId());

      ResponseObj responseObj = new ResponseObj<Map<String, String>>();
      responseObj.setCode(1);
      responseObj.setMessage("成功");
      responseObj.setData(response);

      return responseObj;
  }

  // 订单列表
  @GetMapping("/list")
  public ResponseObj<List<OrderDTO>> list(
      @RequestParam("openid") String openid,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size
  ){

    PageRequest pageRequest = PageRequest.of(page, size);

    List<OrderDTO> orderDTOList =
        orderService.findOrderMasters(pageRequest, openid);

    ResponseObj<List<OrderDTO>> responseObj =
        new ResponseObj<>();
    responseObj.setMessage("response list");
    responseObj.setCode(1);
    responseObj.setData(orderDTOList);

    return responseObj;
  }

  // 取消订单

  // 订单详情

  // 取消订单


}
