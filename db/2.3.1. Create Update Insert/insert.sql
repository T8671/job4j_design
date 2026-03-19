INSERT INTO roles (name) VALUES ('Admin'), ('User'), ('Moderator');

INSERT INTO users (name, role_id) VALUES
	('Иван Иванов', 1),
	('Петр Петров', 2),
    ('Анна Смирнова', 3);

INSERT INTO rules (name) VALUES
    ('read'),
    ('write'),
    ('delete'),
    ('moderate');

INSERT INTO rules_roles (rule_id, role_id) VALUES
    (1, 1), -- Admin может read
    (2, 1), -- Admin может write
    (3, 1), -- Admin может delete
    (1, 3), -- Moderator может read
    (4, 3); -- Moderator может moderate

INSERT INTO categories (name) VALUES
    ('Баг-репорт'),
    ('Улучшение'),
    ('Вопрос');

INSERT INTO states (name) VALUES
    ('Открыта'),
    ('В работе'),
    ('Закрыта');

INSERT INTO items (name, user_id, category_id, state_id) VALUES
    ('Ошибка в авторизации', 1, 1, 1), -- от Ивана, баг, открыта
    ('Добавить dark mode', 2, 2, 2),   -- от Петра, улучшение, в работе
    ('Как изменить пароль?', 3, 3, 3); -- от Анны, вопрос, закрыт

INSERT INTO comments (name, item_id) VALUES
    ('Исправлено в версии 1.2.3', 1),
    ('В планах на Q2', 2),
    ('Ответ отправлен в личку', 3);

INSERT INTO attachs (file_name, file_size, item_id) VALUES
    ('error_screenshot.png', 102400, 1),
    ('mockup_dark_mode.jpg', 204800, 2),
    ('password_guide.pdf', 51200, 3);