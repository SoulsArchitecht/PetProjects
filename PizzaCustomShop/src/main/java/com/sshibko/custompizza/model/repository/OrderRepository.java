package com.sshibko.custompizza.model.repository;

import com.sshibko.custompizza.model.entity.PizzaOrder;

import java.util.Optional;

public interface OrderRepository {

    PizzaOrder save(PizzaOrder order);

    Optional<PizzaOrder> findById(Long id);

}
