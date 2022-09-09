delete from custom_pizza.ingredient_ref;
delete from custom_pizza.pizza;
delete from custom_pizza.pizza_order;

delete from custom_pizza.ingredient;
insert into custom_pizza.ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'TORTILLA');
insert into custom_pizza.ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'TORTILLA');
insert into custom_pizza.ingredient (id, name, type)
values ('BLTO', 'Buckwheat Tortilla', 'TORTILLA');
insert into custom_pizza.ingredient (id, name, type)
values ('CHME', 'Chicken Meat', 'PROTEIN');
insert into custom_pizza.ingredient (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into custom_pizza.ingredient (id, name, type)
values ('SALS', 'Salami Sausage', 'PROTEIN');
insert into custom_pizza.ingredient (id, name, type)
values ('SLTO', 'Sliced Tomatoes', 'VEGGIES');
insert into custom_pizza.ingredient (id, name, type)
values ('DIPI', 'Diced Pineapple', 'VEGGIES');
insert into custom_pizza.ingredient (id, name, type)
values ('CHPE', 'Chili Pepper', 'VEGGIES');
insert into custom_pizza.ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into custom_pizza.ingredient (id, name, type)
values ('MOZA', 'Mozzarella', 'CHEESE');
insert into custom_pizza.Ingredient (id, name, type)
values ('PARM', 'Parmesan', 'CHEESE');
insert into custom_pizza.ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into custom_pizza.ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');
insert into custom_pizza.ingredient (id, name, type)
values ('TOSA', 'Tomato Sauce', 'SAUCE');