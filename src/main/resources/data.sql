-- добавление администратора
INSERT INTO users (
    login,
    password_hash,
    email,
    last_name,
    first_name,
    privileges,
    is_active
) SELECT 'admin', '$2a$08$OQrCVKD2tsV/Xg2jsBqtN.BrjhP./clTgujql5DToSAt5VaRR8vee', 'test@mail.com', 'Админов', 'Админ', 100, true
    WHERE NOT EXISTS (
        SELECT login FROM users WHERE login = 'admin'
    );