package com.alex.java.repo;

import com.alex.java.dataObject.OrderMaster;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepoTest {

  @Autowired OrderMasterRepo orderMasterRepo;

  @Test
  void saveOrderMaster() {
    OrderMaster orderMaster =
        new OrderMaster("123", "weijie", "042430042", "Kinsgrove", "123", new BigDecimal(12), 1, 1);

    orderMasterRepo.save(orderMaster);
  }

  @Test
  void findByBuyerOpenid() {
    // pageable -> page request 之间的转换
    PageRequest pageRequest = PageRequest.of(0, 5);
    // 添加get content会返回真正的所有的查询到的数据
    List<OrderMaster> foundOrderMaster = orderMasterRepo.findByBuyerOpenid("123", pageRequest).getContent();

    System.out.println(foundOrderMaster.size());
    //    assert !foundResultLength.equals(2L);

  }

  @Test
  void findAll() {
    List<OrderMaster> foundResult = orderMasterRepo.findAll();
    System.out.println(foundResult.size());
  }
}
