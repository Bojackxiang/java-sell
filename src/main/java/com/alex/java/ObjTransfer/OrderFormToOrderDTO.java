package com.alex.java.ObjTransfer;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.dataObject.OrderDetail;
import com.alex.java.enums.ResultEnum;
import com.alex.java.exception.OrderException;
import com.alex.java.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderFormToOrderDTO {

  public static OrderDTO transferOrderFormToOrderDto(
      OrderForm orderForm
  ){
    Gson gson = new Gson();
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName(orderForm.getName());
    orderDTO.setBuyerPhone(orderForm.getPhone());
    orderDTO.setBuyerAddress(orderForm.getAddress());
    orderDTO.setBuyerOpenid(orderForm.getOpenid());

    List<OrderDetail> orderDetailList = new ArrayList<>();

    try {
      // 因为传进来的是 JSON， 我们需要吧 JSON 转换为 class
      orderDetailList = gson.fromJson(
          orderForm.getItems(),
          new TypeToken<List<OrderDetail>>() {}.getType()
      );

      orderDTO.setOrderDetailList(orderDetailList);

    } catch(JsonSyntaxException e){
      log.error("转换失败，");
      throw new OrderException(ResultEnum.TRANSFER_ERROR);
    }



    return orderDTO;
  }

}
