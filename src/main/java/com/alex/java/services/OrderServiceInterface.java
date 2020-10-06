package com.alex.java.services;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.dataObject.OrderMaster;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;


public interface OrderServiceInterface {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderMaster findOrder(String orderId);

    OrderDTO findOrderMasters(Pageable pageable);

    OrderDTO cancelOrder(OrderDTO orderDTO);

    OrderDTO finish(OrderDTO orderDTO);

    OrderDTO paid(OrderDTO orderDTO);

}
