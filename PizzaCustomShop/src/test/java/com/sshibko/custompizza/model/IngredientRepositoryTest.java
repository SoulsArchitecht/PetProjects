package com.sshibko.custompizza.model;

import com.sshibko.custompizza.model.entity.Ingredient;
import com.sshibko.custompizza.model.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    JdbcTemplate jdbc;

    @Test
    public void findById() {
        Optional<Ingredient> flto = ingredientRepository.findById("FLTO");
        assertThat(flto.isPresent()).isTrue();
        assertThat(flto.get()).isEqualTo(new Ingredient(
                "FLTO", "Flour Tortilla", Ingredient.Type.TORTILLA));
        Optional<Ingredient> any = ingredientRepository.findById("ANY");
        assertThat(any.isEmpty()).isTrue();
    }
}
