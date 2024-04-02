package org.example.chinesfoodcafe.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "menu")

public class Menu {
    public Menu(Long id, String dish, String description, double price) {
        this.id = id;
        this.dish = dish;
        this.description = description;
        this.price = price;
    }

    public Menu(String dish, String description, double price) {
        this.dish = dish;
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish", unique = true, nullable = false)
    private String dish;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    public Menu() {
    }
}
