package org.example.chinesfoodcafe.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.chinesfoodcafe.utils.Role;

@Data
@Getter
@Builder
@Setter
public class RegistrationRequest {
    public RegistrationRequest(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Size(min = 12, message = "email should have at least 4 characters")
    @NotEmpty
    @Email
    private String email;

    private String password;

    private Role role;
}