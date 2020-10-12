package com.alex.java.services;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.dataObject.OrderMaster;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;


public interface OrderServiceInterface {

    OrderMaster createOrder(OrderDTO orderDTO);

    OrderDTO findOrder(String orderId);

    List<OrderDTO> findOrderMasters(PageRequest pageRequest, String buyerOpenId);

    OrderMaster cancelOrder(OrderDTO orderDTO);

    OrderMaster finish(OrderDTO orderDTO);

    OrderMaster paid(OrderDTO orderDTO);

}
