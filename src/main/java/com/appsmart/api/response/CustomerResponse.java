package com.appsmart.api.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponse {
  private UUID id;
  private String title;
}
