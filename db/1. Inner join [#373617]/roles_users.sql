CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name TEXT,
	role_id INT REFERENCES roles(id)
);

INSERT INTO roles (name) VALUES ('Admin'), ('User'), ('Moderator');

INSERT INTO users (name, role_id) VALUES
	('Иван Иванов', 1),
	('Петр Петров', 2),
    ('Анна Смирнова', 3);

SELECT * FROM users
JOIN roles ON users.role_id = roles.id;

SELECT pp.name, p.name
FROM users AS pp JOIN roles AS p ON pp.role_id = p.id;

SELECT pp.name as Пользователь, p.name as Роль
FROM users AS pp JOIN roles AS p ON pp.role_id = p.id;

SELECT pp.name as "Имя пользователя", p.name as Роль
FROM users AS pp JOIN roles p ON pp.role_id = p.id;