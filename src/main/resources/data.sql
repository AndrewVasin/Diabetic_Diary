-- добавление администратора
insert into users (first_name,
                   last_name,
                   email,
                   login,
                   password_hash,
                   user_role,
                   is_active)
select 'admin',
       'admin',
       'admin@mail.com',
       'admin',
       '$2y$10$0QVl7cn2GjxRikz1j/zwou91n.1NftL7YKgnvOfujfQJK2jqCXUK6',
       'ROLE_ADMIN',
       true
where NOT exists(
        SELECT login FROM users WHERE login = 'admin'
    );