package com.sshibko.custompizza.model;

import com.sshibko.custompizza.model.entity.Ingredient;
import com.sshibko.custompizza.model.entity.Pizza;
import com.sshibko.custompizza.model.entity.PizzaOrder;
import com.sshibko.custompizza.model.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void saveOrderWithTwoPizzas() {
        PizzaOrder order = new PizzaOrder();

        order.setDeliveryName("Test Name");
        order.setDeliveryStreet("Test Street");
        order.setDeliveryCity("Test City");
        order.setDeliveryRegion("TR");
        order.setDeliveryZip("000000");
        order.setCcNumber("1111222233334444");
        order.setCcExpiration("1/24");
        order.setCcCVV("111");

        Pizza pizza1 = new Pizza();
        pizza1.setName("Pizza one");
        pizza1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.TORTILLA));
        pizza1.addIngredient(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        pizza1.addIngredient(new Ingredient("MOZA", "Mozarella", Ingredient.Type.CHEESE));
        pizza1.addIngredient(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        order.addPizza(pizza1);

        Pizza pizza2 = new Pizza();
        pizza1.setName("Pizza two");
        pizza1.addIngredient(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.TORTILLA));
        pizza1.addIngredient(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        pizza1.addIngredient(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        pizza1.addIngredient(new Ingredient("SLTO", "Sliced Tomatoes", Ingredient.Type.VEGGIES));
        order.addPizza(pizza1);
        order.addPizza(pizza2);

        PizzaOrder savedOrder = orderRepository.save(order);
        assertThat(savedOrder.getId()).isNotNull();

        PizzaOrder fetchedOrder = orderRepository.findById(savedOrder.getId()).get();
        assertThat(fetchedOrder.getDeliveryName()).isEqualTo("Test Name");
        assertThat(fetchedOrder.getDeliveryStreet()).isEqualTo("Test Street");
        assertThat(fetchedOrder.getDeliveryCity()).isEqualTo("Test City");
        assertThat(fetchedOrder.getDeliveryRegion()).isEqualTo("TR");
        assertThat(fetchedOrder.getDeliveryZip()).isEqualTo("000000");
        assertThat(fetchedOrder.getCcNumber()).isEqualTo("1111222233334444");
        assertThat(fetchedOrder.getCcExpiration()).isEqualTo("1/24");
        assertThat(fetchedOrder.getCcCVV()).isEqualTo("111");
        assertThat(fetchedOrder.getPlacedAt()).isEqualTo(savedOrder.getPlacedAt());
        List<Pizza> pizzaList = fetchedOrder.getPizzas();
        assertThat(pizzaList.size()).isEqualTo(3);
        //assertThat(pizzaList).containsExactlyInAnyOrder(pizza1, pizza2);
    }
}
