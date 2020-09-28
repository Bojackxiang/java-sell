package com.alex.java.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductInfo {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String productName;

  @JsonProperty("price")
  private BigDecimal productPrice;

  @JsonProperty("description")
  private String productDescription;

  @JsonProperty("icon")
  private String productIcon;


}
