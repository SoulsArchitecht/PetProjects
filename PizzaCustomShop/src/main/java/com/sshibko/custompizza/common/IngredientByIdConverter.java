package com.sshibko.custompizza.common;

import com.sshibko.custompizza.model.entity.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap();

    public IngredientByIdConverter() {
        ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.TORTILLA));
        ingredientMap.put("COTO", new Ingredient("COTO","Corn Tortilla", Ingredient.Type.TORTILLA));
        ingredientMap.put("BLTO", new Ingredient("BLTO", "Buckwheat Tortilla", Ingredient.Type.TORTILLA));
        ingredientMap.put("CHME", new Ingredient("CHME", "Chicken Meat", Ingredient.Type.PROTEIN));
        ingredientMap.put("CRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientMap.put("SALS", new Ingredient("SALS", "Salami Sausage", Ingredient.Type.PROTEIN));
        ingredientMap.put("SLTO", new Ingredient("SLTO", "Sliced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("DIPI", new Ingredient("DIPI", "Diced Pineapple", Ingredient.Type.VEGGIES));
        ingredientMap.put("CHPE", new Ingredient("CHPE", "Chili Pepper", Ingredient.Type.VEGGIES));
        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("MOZA", new Ingredient("MOZA", "Mozzarella", Ingredient.Type.CHEESE));
        ingredientMap.put("PARM", new Ingredient("PARM", "Parmesan", Ingredient.Type.CHEESE));
        ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        ingredientMap.put("TOSA", new Ingredient("TOSA", "Tomato Sauce", Ingredient.Type.SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
