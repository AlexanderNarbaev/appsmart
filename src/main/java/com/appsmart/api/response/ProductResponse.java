package com.appsmart.api.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductResponse {
  private UUID id;
  private String title;
  private String description;
  private BigDecimal price;
}
