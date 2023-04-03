package com.diary.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Аккаунт пользователя
 * Поля:
 *
 * id - идентификатор
 * firstName - имя
 * lastName - фамилия
 * email - почта
 * login - логин
 * passwordHash - хеш пароля
 * userRole - привилегии (админ/пользователь)
 * isActive - статус учетной записи пользователя (активна/не активна)
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "login", length = 64, nullable = false, unique = true)
    private String login;

    @Column(name = "password_hash", length = 64, nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", length = 12, nullable = false)
    private UserRole userRole;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    // сеттер пароля
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
