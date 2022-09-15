package com.sshibko.custompizza.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ingredients")
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    public Ingredient() {

    }

    public enum Type {
        TORTILLA, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
