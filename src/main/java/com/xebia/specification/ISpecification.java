package com.xebia.specification;

public interface ISpecification<T> {

    Boolean isSatisfiedBy(T obj);
}

