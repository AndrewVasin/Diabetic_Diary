-- таблица со списком пользователей
CREATE TABLE IF NOT EXISTS users (
    id         INT NOT NULL AUTO_INCREMENT,                             -- идентификатор
    login      VARCHAR(64) NOT NULL UNIQUE,                             -- логин
    password_hash   VARCHAR(64) NOT NULL UNIQUE,                        -- хэш пароля в bcrypt
    email      VARCHAR(255) NOT NULL UNIQUE,                            -- email
    last_name  VARCHAR(255) NOT NULL,                                   -- фамилия
    first_name VARCHAR(255) NOT NULL,                                   -- имя
    privileges TINYINT NOT NULL DEFAULT 0,                              -- полномочия
    is_active  BOOLEAN NOT NULL DEFAULT TRUE,                           -- включён/выключен
    PRIMARY KEY (id)
    );