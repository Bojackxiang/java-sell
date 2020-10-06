package com.alex.java.dataObject;

import com.alex.java.enums.OrderStatusEnum;
import com.alex.java.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

  /** 订单id. */
  @Id private String orderId;

  /** 买家名字. */
  private String buyerName;

  /** 买家手机号. */
  private String buyerPhone;

  /** 买家地址. */
  private String buyerAddress;

  /** 买家微信Openid. */
  private String buyerOpenid;

  /** 订单总金额. */
  private BigDecimal orderAmount;

  /** 订单状态, 默认为0新下单. */
  private Integer orderStatus;

  /** 支付状态, 默认为0未支付. */
  private Integer payStatus;

  /** 创建时间. */
  private Date createTime;

  /** 更新时间. */
  private Date updateTime;

  /**
   * 为了解决
   * 'Basic' attribute type should not be a container
   * */
  @OneToMany
  private List<OrderDetail> orderDetailList;

  public OrderMaster() {}

  public OrderMaster(
      String orderId,
      String buyerName,
      String buyerPhone,
      String buyerAddress,
      String buyerOpenid,
      BigDecimal orderAmount,
      Integer orderStatus,
      Integer payStatus) {
    this.orderId = orderId;
    this.buyerName = buyerName;
    this.buyerPhone = buyerPhone;
    this.buyerAddress = buyerAddress;
    this.buyerOpenid = buyerOpenid;
    this.orderAmount = orderAmount;
    this.orderStatus = orderStatus;
    this.payStatus = payStatus;
  }
}
