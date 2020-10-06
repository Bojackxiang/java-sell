package com.alex.java.repo;

import com.alex.java.dataObject.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class OrderDetailRepoTest {

  @Autowired OrderDetailRepo orderDetailRepo;

  @Test
  void saveOrderDetailRepo() {
    OrderDetail orderDetail =
        new OrderDetail("123", "123", "123", "汽车玩具", new BigDecimal(12.2), 1, "icon string");

    OrderDetail savingResult = orderDetailRepo.save(orderDetail);
    assert savingResult != null;
  }

  @Test
  void findOrderDetailByOrderId() {
    List<OrderDetail> foundOrderDetail = orderDetailRepo.findByOrOrderId("123");
    assert foundOrderDetail.size() != 0;
    System.out.printf("size is %d", foundOrderDetail.size());
  }
}
