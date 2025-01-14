package org.example.chinesfoodcafe.model;
//

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    private final String email;

    private final String password;
}
