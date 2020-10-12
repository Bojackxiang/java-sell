/*
 * 创建一个表单，用来接受用户传进来的数据
 */

package com.alex.java.form;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class OrderForm {
  /**
   * 买家姓名
   */
  @NotNull()
  private String name;

  /**
   * 买家手机号
   */
  @NotNull()
  private String phone;

  /**
   * 买家地址
   */
  @NotNull()
  private String address;

  /**
   * 买家微信openid
   */
  @NotNull()
  private String openid;

  /**
   * 购物车
   */
  @NotNull()
  private String items;

}
