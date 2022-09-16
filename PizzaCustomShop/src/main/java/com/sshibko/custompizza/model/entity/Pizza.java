package com.sshibko.custompizza.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pizza")
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long" )
    private String name;

    @NotNull
    @Size(min = 3, message = "You must choose at least 3 ingredient not similar type")
    @ManyToMany
    private List<Ingredient> ingredientList = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredientList.add(ingredient);
    }
}
