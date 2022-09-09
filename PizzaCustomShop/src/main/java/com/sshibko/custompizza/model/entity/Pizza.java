package com.sshibko.custompizza.model.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pizza {

    private Long id;

    private LocalDateTime createdAt;

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long" )
    private String name;

    @NotNull
    @Size(min = 3, message = "You must choose at least 3 ingredient not similar type")
    private List<IngredientRef> ingredientList;
}
