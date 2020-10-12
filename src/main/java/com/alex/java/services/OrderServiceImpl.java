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
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

  @Autowired
  ProductInfoServiceImpl productInfoService;

  @Autowired
  OrderDetailRepo orderDetailRepo;

  @Autowired
  OrderMasterRepo orderMasterRepo;

  // 已经测试完成 ✅
  @Override
  @Transactional
  public OrderMaster createOrder(OrderDTO orderDTO) throws RuntimeException {
    // 订单的总价格 -> ORDER MASTER ID
    String orderId = KeyUtil.genUniqueKey();

    // 这边的的order detail应该是购物车里面选择的东西，
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
      orderDetail.setOrderId(orderId);

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
    OrderMaster createOrder = orderMasterRepo.save(orderMaster);

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
    return createOrder;
  }

  // 等待测试
  @Override
  public OrderDTO findOrder(String orderId) {
    // 通过 ID order master id 来寻找一个 orderMaster
    OrderMaster orderMaster = orderMasterRepo.findById(orderId)
        .orElse(null);

    // 如果没有找到order master
    if(orderMaster == null) {
      throw  new OrderException(ResultEnum.NO_ORDER_MASTER);
    }

    // 通过 order_id 来查询 order_details
    List<OrderDetail> foundOrderDetailList = orderDetailRepo.findByOrOrderId(orderId);

    // 创建一个order master，来组成数据，
    OrderDTO createdOrderDTO = new OrderDTO();
    BeanUtils.copyProperties(orderMaster, createdOrderDTO);
    createdOrderDTO.setOrderDetailList(foundOrderDetailList);

  return createdOrderDTO;
  }

  // 等待测试
  @Override
  public List<OrderDTO> findOrderMasters(PageRequest pageRequest, String buyerOpenId) {
    PageRequest findOrderMastersPageable = PageRequest.of(0, 1);
    List<OrderDTO> orderDetailList = new ArrayList<>();

    // 先把所有的 order master 查询到
    List<OrderMaster> foundOrderMaster
        = orderMasterRepo.
            findByBuyerOpenid( buyerOpenId, findOrderMastersPageable)
            .getContent();

    // 如果order master 是 [], 那么就报错
    if(foundOrderMaster.size() == 0){
      throw  new OrderException(ResultEnum.NO_ORDER_MASTER);
    }

    for(OrderMaster orderMaster: foundOrderMaster) {
      OrderDTO tempOrderDTO = new OrderDTO();
      String orderId = orderMaster.getOrderId();
      List<OrderDetail> createOrderList = orderDetailRepo
          .findByOrOrderId(orderId);

      // 检查是不是有 order detail
      if(createOrderList.size() == 0){
        throw  new OrderException(ResultEnum.NO_ORDER_DETAIL_FOUND);
      }

      BeanUtils.copyProperties(orderMaster, tempOrderDTO);
      tempOrderDTO.setOrderDetailList(createOrderList);
      orderDetailList.add(tempOrderDTO);
    }

    return orderDetailList;
  }

  // 等待测试
  @Override
  public OrderMaster cancelOrder(OrderDTO orderDTO) {
    // 查看订单状态，只有处于处理的才能完结

    OrderMaster foundOrderMaster =
        orderMasterRepo
            .findById(orderDTO.getOrderId())
            .orElse(null);

    if(foundOrderMaster == null) {
      throw new OrderException(ResultEnum.NO_ORDER_MASTER);
    }

    foundOrderMaster
        .setOrderStatus(
            OrderStatusEnum.CANCEL.getCode());

    return orderMasterRepo
        .save(foundOrderMaster);
  }

  // 等待测试
  @Override
  public OrderMaster finish(OrderDTO orderDTO) {
    OrderMaster foundOrderMaster =
        orderMasterRepo.findById(orderDTO.getOrderId())
            .orElse(null);

    if(foundOrderMaster == null) {
      throw new OrderException(ResultEnum.NO_ORDER_MASTER);
    }

    foundOrderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());

    return orderMasterRepo.save(foundOrderMaster);

  }

  // 等待测试
  @Override
  public OrderMaster paid(OrderDTO orderDTO) {
    OrderMaster foundOrderMaster =
        orderMasterRepo.findById(orderDTO.getOrderId())
        .orElse(null);

    if(foundOrderMaster == null) {
      throw new OrderException(ResultEnum.NO_ORDER_MASTER);
    }

    foundOrderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
    foundOrderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());

    return orderMasterRepo.save(foundOrderMaster);

  }
}
