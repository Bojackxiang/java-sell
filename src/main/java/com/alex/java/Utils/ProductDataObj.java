package com.alex.java.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductDataObj {
  /*
   * 这个 主要是用来存放 response 中的各种product的data结构
   */

  @JsonProperty("name")
  private String categoryName;

  @JsonProperty("type")
  private Integer categoryTypes;

  @JsonProperty("food")
  private List<ProductInfo> productInfoList;

}
