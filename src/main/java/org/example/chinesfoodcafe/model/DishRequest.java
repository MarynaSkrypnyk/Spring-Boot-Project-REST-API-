package org.example.chinesfoodcafe.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Builder
@Setter
public class DishRequest {

    @NotNull
    private String dish;

    @NotNull
    private String description;

    @Min(value = 10, message = "min price value 10 grn")
    private double price;
}
