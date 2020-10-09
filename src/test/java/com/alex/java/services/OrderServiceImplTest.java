package com.alex.java.services;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.dataObject.OrderDetail;
import com.alex.java.dataObject.ProductInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

@SpringBootTest
class OrderServiceImplTest {

  @Autowired OrderServiceImpl orderService;

  @Test
  void createOrder() {
    OrderDTO orderDto = new OrderDTO();
    orderDto.setBuyerName("廖师兄");
    orderDto.setBuyerAddress("幕课网");
    orderDto.setBuyerPhone("123456789012");
    orderDto.setBuyerOpenid("BUYER_OPEN_ID");

    List<OrderDetail> orderDetailList = new ArrayList<>();
    OrderDetail o1 = new OrderDetail();
    o1.setProductId("1");
    o1.setProductQuantity(1);

    // 测试transactional
    OrderDetail o2 = new OrderDetail();
    o2.setProductId("2");
    o2.setProductQuantity(1);

    OrderDetail o3 = new OrderDetail();
    o3.setProductId("3");
    o3.setProductQuantity(1);

    orderDetailList.add(o1);
    orderDetailList.add(o2);
    orderDetailList.add(o3);

    orderDto.setOrderDetailList(orderDetailList);

    orderService.createOrder(orderDto);
  }

  @Test
  void findOrder() {}

  @Test
  void findOrderMasters() {}

  @Test
  void cancelOrder() {}

  @Test
  void finish() {}

  @Test
  void paid() {}
}
