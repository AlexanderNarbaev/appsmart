package com.appsmart.api.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProduct {
  private String title;
  private String description;
  private BigDecimal price;
}
