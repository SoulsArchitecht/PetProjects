package com.sshibko.custompizza.model.repository;

import com.sshibko.custompizza.model.entity.PizzaOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {

}
