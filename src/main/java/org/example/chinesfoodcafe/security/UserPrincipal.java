package org.example.chinesfoodcafe.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
public class UserPrincipal implements UserDetails {
    private final Long userId;
    private final String email;

    @JsonIgnore
    private final String password;

    private final List<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // <-- Very important to not forget
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // <-- Very important to not forget
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // <-- Very important to not forget
    }

    @Override
    public boolean isEnabled() {
        return true; // <-- Very important to not forget
    }

//    (інтерфейс, який представляє ентіті, я можу бути залогінене, як айді карта)
//    Для работы со Spring Security нужно имплементировать интерфейс UserDetails -
//    в нём инкапсулированы основные данные о пользователе, необходимые для процесса
//    аутентификации и авторизации в Spring Security.
//    (чи може юзер переходити на певны ендпоънти, по имени и роли )

}
