package com.alex.java.services;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.Utils.KeyUtil;
import com.alex.java.dataObject.OrderDetail;
import com.alex.java.dataObject.OrderMaster;
import com.alex.java.dataObject.ProductInfo;
import com.alex.java.enums.ResultEnum;
import com.alex.java.exception.OrderException;
import com.alex.java.repo.OrderDetailRepo;
import com.alex.java.repo.OrderMasterRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

  @Autowired
  ProductInfoServiceImpl productInfoService;

  @Autowired
  OrderDetailRepo orderDetailRepo;

  @Autowired
  OrderMasterRepo orderMasterRepo;

  @Override
  public OrderDTO createOrder(OrderDTO orderDTO) throws RuntimeException {
    // 订单的总价格 -> ORDER MASTER ID
    UUID orderId = KeyUtil.generateUUID();
    String strinigiedUUID = orderId.toString();

    List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
    // 订单总价 -> ORDER MASTER TOTAL PRICE
    BigDecimal totalPrice = new BigDecimal(0);
    for (OrderDetail orderDetail : orderDetailList) {
      ProductInfo productInfo =
          productInfoService.findOneProductInfoById(orderDetail.getProductId());
      if (productInfo == null) {
        throw new OrderException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      totalPrice =
          totalPrice.add(
              orderDetail
                  .getProductPrice()
                  .multiply(new BigDecimal(orderDetail.getProductQuantity())));

      // 属性拷贝和设置order id && order detail id
      BeanUtils.copyProperties(productInfo, orderDetail);
      orderDetail.setDetailId(KeyUtil.generateUUID().toString());
      orderDetail.setOrderId(strinigiedUUID);

      orderDetailRepo.save(orderDetail);
    }

    // 处理order master
    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId(strinigiedUUID);
    orderMaster.setOrderAmount(totalPrice);
    BeanUtils.copyProperties(orderDTO, orderMaster);
    // 将 order master 存入表中 -> SAVE ORDER TO TABLE
    orderMasterRepo.save(orderMaster);
    // RETURN ORDER DTO
    return orderDTO;
  }

  @Override
  public OrderMaster findOrder(String orderId) {
    return null;
  }

  @Override
  public OrderDTO findOrderMasters(Pageable pageable) {
    return null;
  }

  @Override
  public OrderDTO cancelOrder(OrderDTO orderDTO) {
    return null;
  }

  @Override
  public OrderDTO finish(OrderDTO orderDTO) {
    return null;
  }

  @Override
  public OrderDTO paid(OrderDTO orderDTO) {
    return null;
  }
}
