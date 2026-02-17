package com.appsmart.api.converter;

public interface Converter<A, B> {
  B to(A input);
}
