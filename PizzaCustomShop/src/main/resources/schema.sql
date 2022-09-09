CREATE SCHEMA IF NOT EXISTS custom_pizza;
create table if not exists custom_pizza.pizza_order (
                                          id identity,
                                          delivery_name varchar(64) not null,
    delivery_street varchar(64) not null,
    delivery_city varchar(64) not null,
    delivery_region varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(64) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
    );

create table if not exists custom_pizza.pizza (
                                    id identity,
                                    name varchar(50) not null,
    pizza_order bigint not null,
    pizza_order_key bigint not null,
    created_at timestamp not null
    );

create table if not exists custom_pizza.ingredient_ref (
    ingredient varchar(16) not null,
    pizza bigint not null,
    pizza_key bigint not null
    );


create table if not exists custom_pizza.Ingredient (
    id varchar(16) not null,
    name varchar(25) not null,
    type varchar(16) not null
    );


alter table custom_pizza.pizza
    add foreign key (pizza_order) references pizza_order(id);
alter table custom_pizza.ingredient_ref
    add foreign key (ingredient) references ingredient(id);