package com.appsmart.api.converter;

public interface Converter<A, B> {
  A from(B input);

  Iterable<A> allFrom(Iterable<B> input);

  B to(A input);

  Iterable<B> allTo(Iterable<A> input);
}
