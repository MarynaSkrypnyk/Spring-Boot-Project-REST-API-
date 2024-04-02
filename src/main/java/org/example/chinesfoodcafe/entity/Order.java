package org.example.chinesfoodcafe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.chinesfoodcafe.utils.Delivery;
import org.example.chinesfoodcafe.utils.Payment;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    public Order(String dish, String email, String location, int number, Delivery delivery, Payment payment, String note) {
        this.dish = dish;
        this.email = email;
        this.location = location;
        this.number = number;
        this.delivery = delivery;
        this.payment = payment;
        this.note = note;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish", unique = true, nullable = false)
    private String dish;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "number", nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery", nullable = false)
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment", nullable = false)
    private Payment payment;

    @Column(name = "note")
    private String note;

    public Order() {

    }
}
