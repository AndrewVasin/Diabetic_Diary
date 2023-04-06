-- таблица со списком пользователей
CREATE TABLE IF NOT EXISTS users
(
    id            INT          NOT NULL AUTO_INCREMENT primary key, -- идентификатор
    first_name    VARCHAR(255) NOT NULL,                            -- имя
    last_name     VARCHAR(255) NOT NULL,                            -- фамилия
    email         VARCHAR(255) NOT NULL UNIQUE,                     -- email
    login         VARCHAR(64)  NOT NULL UNIQUE,                     -- логин
    password_hash VARCHAR(64)  NOT NULL UNIQUE,                     -- хэш пароля в bcrypt
    user_role     VARCHAR(12)  NOT NULL,                            -- роль
    is_active     BOOLEAN      NOT NULL DEFAULT TRUE                -- включён/выключен
);

CREATE TABLE IF NOT EXISTS glycemia
(
    id             bigint primary key not null auto_increment,
    comment        varchar(255),
    date_time      datetime,
    measuring_type varchar(255),
    sugar_level    float
);