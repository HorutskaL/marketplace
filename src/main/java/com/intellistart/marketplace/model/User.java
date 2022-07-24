package com.intellistart.marketplace.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "User")
@ToString(exclude = {"products"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @NotEmpty(message = "First name should not be empty")
    @Size(min = 3, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 3, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastName;
    @Min(value = 0, message = "Amount of money should be not less than 0")
    private double amountOfMoney;
    private String fullName = firstName + lastName;

    public User(String firstName) {
        this.firstName = firstName;
    }

    public User(String firstName, String lastName, double amountOfMoney, List<Product> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfMoney = amountOfMoney;
        this.products = products;
    }

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    private List<Product> products;
}
