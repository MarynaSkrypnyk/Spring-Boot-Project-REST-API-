package org.example.chinesfoodcafe.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.chinesfoodcafe.utils.Delivery;
import org.example.chinesfoodcafe.utils.Payment;

@Data
@Getter
@Setter
public class OrderRequest {
    @NotEmpty
    private String dish;

    @NotEmpty
    private String location;

    @NotNull
    @Min(value = 10000, message = "number should have 4 number")
    @Max(value = 99999, message = "number should have 4 number")
    private int number;

    @NotNull
    private Delivery delivery;

    @NotNull
    private Payment payment;

    @NotNull
    @Email
    private String email;

    private String note;

}
