package com.sshibko.custompizza.controller;

import com.sshibko.custompizza.model.entity.Ingredient;
import com.sshibko.custompizza.model.entity.Pizza;
import com.sshibko.custompizza.model.entity.PizzaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.TORTILLA),
            new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.TORTILLA),
            new Ingredient("BLTO", "Buckwheat Tortilla", Ingredient.Type.TORTILLA),
            new Ingredient("CHME", "Chicken Meat", Ingredient.Type.PROTEIN),
            new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            new Ingredient("SALS", "Salami Sausage", Ingredient.Type.PROTEIN),
            new Ingredient("SLTO", "Sliced Tomatoes", Ingredient.Type.VEGGIES),
            new Ingredient("DIPI", "Diced Pineapple", Ingredient.Type.VEGGIES),
            new Ingredient("CHPE", "Chili Pepper", Ingredient.Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            new Ingredient("MOZA", "Mozzarella", Ingredient.Type.CHEESE),
            new Ingredient("PARM", "Parmesan", Ingredient.Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE),
            new Ingredient("TOSA", "Tomato Sauce", Ingredient.Type.SAUCE)

        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processPizza(Pizza pizza, @ModelAttribute PizzaOrder pizzaOrder) {
        pizzaOrder.addPizza(pizza);
        log.info("Processing pizza: {}", pizza);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType (
        List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

}
