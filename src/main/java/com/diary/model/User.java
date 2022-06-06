package com.diary.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;

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
 * privileges - привилегии (админ/пользователь)
 * isActive - статус учетной записи пользователя (активна/не активна)
 */

@Entity
@Table(name = "users")
public class User {
    public static Byte PRIVILEGES_ADMIN  = (byte) 100;
    public static Byte PRIVILEGES_COMMON = (byte) 0;
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
    private String passwordHash;

    @Column(name = "privileges", nullable = false)
    private Byte privileges;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public User(
            final String  login,
            final String  password,
            final String  email,
            final String  lastName,
            final String  firstName,
            final Byte    privileges,
            final Boolean isActive
    ) {
        this.id           = null;
        this.login        = login;
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        this.email        = email;
        this.lastName     = lastName;
        this.firstName    = firstName;
        this.privileges   = privileges;
        this.isActive     = isActive;
    }

    public User() {}

    // геттер Id
    public Integer getId() {
        return this.id;
    }

    // геттер логина
    public String getLogin() {
        return this.login;
    }

    // сеттер логина
    public void setLogin(final String login) {
        this.login = login;
    }

    // геттер хэша пароля
    public String getPasswordHash() {
        return this.passwordHash;
    }

    // сеттер пароля
    public void setPassword(final String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // проверка соответствия пароля
    public boolean isValidPassword(final String password) {
        return BCrypt.checkpw(password, this.passwordHash);
    }

    // геттер email
    public String getEmail() {
        return this.email;
    }

    // сеттер email
    public void setEmail(final String email) {
        this.email = email;
    }

    // геттер имени
    public String getFirstName() {
        return this.firstName;
    }

    // сеттер имени
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    // геттер фамилии
    public String getLastName() {
        return this.lastName;
    }

    // сеттер фамилии
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    // геттер привилегий
    public Byte getPrivileges() {
        return this.privileges;
    }

    // сеттер привилегий
    public void setPrivileges(final Byte privileges) {
        this.privileges = privileges;
    }

    // геттер статуса учетной записи
    public Boolean getIsActive() {
        return this.isActive;
    }

    // сеттер статуса учетной записи
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }
}
