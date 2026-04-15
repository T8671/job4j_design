-- create
CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE car_engines (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name TEXT,
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

-- insert
INSERT INTO car_bodies(name) VALUES ('Седан');
INSERT INTO car_bodies(name) VALUES ('Хэтчбек');
INSERT INTO car_bodies(name) VALUES ('Универсал');
INSERT INTO car_bodies(name) VALUES ('Внедорожник');

INSERT INTO car_engines(name) VALUES ('Бензин 1.6');
INSERT INTO car_engines(name) VALUES ('Дизель 2.0');
INSERT INTO car_engines(name) VALUES ('Электро');

INSERT INTO car_transmissions(name) VALUES('Механика');
INSERT INTO car_transmissions(name) VALUES('Автомат');
INSERT INTO car_transmissions(name) VALUES('Робот');

INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Toyota Camry', 1, 1, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Ford Focus', 2, 1, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('BMW X5', 4, 2, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Tesla Model 3', 1, 3, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Kia Sportage', 4, 1, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Skoda Octavia', 3, 1, 1);

-- fetch
/*Вывести список всех машин и все привязанные к ним детали.
Нужно учесть, что каких-то деталей машина может и не содержать.
В таком случае значение может быть null при выводе (например, название двигателя null);
Пример "шапки" при выводе:
id, car_name, body_name, engine_name, transmission_name*/
SELECT c.id, c.name AS car_name,
       cb.name AS body_name,
       ce.name AS engine_name,
       ct.name AS transmission_name
FROM cars c
LEFT JOIN car_bodies cb ON c.body_id = cb.id
LEFT JOIN car_engines ce ON c.engine_id = ce.id
LEFT JOIN car_transmissions ct ON c.transmission_id = ct.id;

/*Вывести кузова, которые не используются НИ в одной машине.
"Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи, ссылающие на таблицу car_bodies.
Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", а при добавлении в таблицу cars указали только внешние ключи на записи "седан" и "хэтчбек".
Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин нет тех, что обладают таким кузовом;*/
SELECT c.id, c.name AS car_name,
       cb.name AS body_name
FROM cars c
LEFT JOIN car_bodies cb ON c.engine_id = cb.id
WHERE c.id IS NULL;

/*Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;*/
SELECT c.id, c.name AS car_name,
       ce.name AS engine_name
FROM cars c
LEFT JOIN car_engines ce ON c.body_id = ce.id
WHERE c.id IS NULL;

/*Вывести коробки передач,
которые не используются НИ в одной машине, аналогично п.2.*/
SELECT c.id, c.name AS car_name,
       ct.name AS transmission_name
FROM cars c
LEFT JOIN car_transmissions ct ON c.transmission_id = ct.id
WHERE c.id IS NULL;