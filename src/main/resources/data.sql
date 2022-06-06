-- добавление администратора
INSERT INTO users (
    login,
    password_hash,
    email,
    last_name,
    first_name,
    privileges,
    is_active
) SELECT 'admin', '$2a$10$fwaMoOUHyiPhpUJMF4PpGuZMuvSHg2N60p/VKOnjEZih24fLzRjdm', 'test@mail.com', 'Админов', 'Админ', 100, true
    WHERE NOT EXISTS (
        SELECT login FROM users WHERE login = 'admin'
    );