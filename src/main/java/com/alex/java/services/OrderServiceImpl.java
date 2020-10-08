package com.alex.java.services;

import com.alex.java.DTO.OrderDTO.OrderDTO;
import com.alex.java.Utils.KeyUtil;
import com.alex.java.dataObject.OrderDetail;
import com.alex.java.dataObject.OrderMaster;
import com.alex.java.dataObject.ProductInfo;
import com.alex.java.enums.OrderStatusEnum;
import com.alex.java.enums.PayStatusEnum;
import com.alex.java.enums.ResultEnum;
import com.alex.java.exception.OrderException;
import com.alex.java.repo.OrderDetailRepo;
import com.alex.java.repo.OrderMasterRepo;
import javax.transaction.Transactional;
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

  /*
   *
   *
   */
  @Override
  @Transactional
  public OrderDTO createOrder(OrderDTO orderDTO) throws RuntimeException {
    // 订单的总价格 -> ORDER MASTER ID
    String orderId = KeyUtil.genUniqueKey();
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
              productInfo
                  .getProductPrice()
                  .multiply(new BigDecimal(orderDetail.getProductQuantity())));

      // 属性拷贝和设置order id && order detail id
      BeanUtils.copyProperties(productInfo, orderDetail);
      orderDetail.setDetailId(KeyUtil.genUniqueKey());
      orderDetail.setOrderId(strinigiedUUID);

      orderDetailRepo.save(orderDetail);
    }

    // 处理order master
    // Bean utils 还是要放在最前面
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);
    orderMaster.setOrderId(KeyUtil.genUniqueKey());
    orderMaster.setOrderAmount(totalPrice);
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


    // 将 order master 存入表中 -> SAVE ORDER TO TABLE
    orderMasterRepo.save(orderMaster);

    // 你买的每个商品都要进行遍历，和扣库存: List -> orderDetailList
    for(OrderDetail itemInfo: orderDetailList){
      /*
       * 修改完库存之后的 商品，
       * 回滚的问题
       */
      // 找到 item 的 id
      String productId = itemInfo.getProductId();
      // check stock: lock topic 错误好像是在这里
      System.out.println(productId);
      ProductInfo foundResult = productInfoService.findOneProductInfoById(productId);
      Integer stockNumber = foundResult.getProductStock();
      if(stockNumber <= 0) {
        throw new OrderException(ResultEnum.PRODUCT_NOT_ENOUGH);
      }
      // 减少一个库存
      productInfoService.decreaseStockingByOne(productId);

    }

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
