package com.alex.java.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alex.java.dataObject.OrderDetail;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrOrderId(String orderId);

}
