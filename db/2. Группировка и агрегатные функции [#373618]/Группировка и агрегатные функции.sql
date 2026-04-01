CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE people
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES people (id)
);

INSERT INTO devices(name, price) VALUES
    ('Ноутбук', 100000),
    ('Телефон', 50000),
    ('Планшет', 25000);

INSERT INTO people(name) VALUES
    ('Иван'),
    ('Мария'),
    ('Алексей');

INSERT INTO devices_people(device_id, people_id) VALUES
    (1, 1), -- Иван использует Ноутбук
    (1, 2), -- Мария использует Ноутбук
    (2, 3), -- Алексей использует Телефон
    (3, 3); -- Алексей использует Планшет

SELECT AVG(price) FROM devices;

SELECT p.name, AVG(d.price)
FROM devices_people dp
JOIN people p ON people_id = p.id
JOIN devices d ON device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;
