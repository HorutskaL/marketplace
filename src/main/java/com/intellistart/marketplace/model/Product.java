package com.intellistart.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "users")
public class Product extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @NotEmpty(message = "Product name name should not be empty")
    private String name;

    public Product(String name, double price, List<User> users) {
        this.name = name;
        this.price = price;
        this.users = users;
    }

    @NotEmpty(message = "Product price name should not be empty")
    @Min(value = 0, message = "Product price should be grater than 0")
    private double price;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    @JsonIgnore
    private List<User> users;
}
