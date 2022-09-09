package com.sshibko.custompizza.model.repository;

import com.sshibko.custompizza.model.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
