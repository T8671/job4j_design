-- create
CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name TEXT,
    expired DATE,
    price INT,
    type_id INT REFERENCES type(id)
);

-- insert
INSERT INTO type(name) VALUES
  ('СЫР'),
  ('МОЛОКО'),
  ('МЯСО'),
  ('ХЛЕБ'),
  ('МОРОЖЕНОЕ');

INSERT INTO product(name, expired, price, type_id) VALUES
  (('Сыр плавленный'), ('2025-01-01'), (200), (1)),
  (('Сыр моцарелла'), ('2024-04-01'), (300), (1)),
  (('Молоко 3.2%'), ('2024-04-05'), (200), (2)),
  (('Мороженое ваниль'), ('2024-03-15'), (150), (5)),
  (('Мороженое шоколад'), ('2024-04-10'), (160), (5)),
  (('Колбаса'), ('2024-04-02'), (400), (3)),
  (('Хлеб белый'), ('2024-04-03'), (50), (4)),
  (('Сыр Российский'), ('2024-03-30'), (250), (1)),
  (('Молоко 1%'), ('2024-04-01'), (180), (2)),
  (('Сыр твердый'), ('2024-05-01'), (200), (1)),
  (('Молоко 2.5%'), ('2024-04-02'), (200), (2));

-- fetch
/*Написать запрос получение всех продуктов с типом "СЫР"*/
SELECT p.*, t.name FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'СЫР';

/*Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"*/
SELECT p.*, t.name FROM product p
JOIN type t ON p.type_id = t.id
WHERE p.name ILIKE '%мороженое%';

/*Написать запрос, который выводит все продукты, срок годности которых уже истек*/
SELECT * FROM product
WHERE expired < CURRENT_DATE - INTERVAL '7 days';

/*Написать запрос, который выводит самый дорогой продукт*/
SELECT * FROM product
WHERE price = (
  SELECT MAX(price) FROM product
);

/*Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество*/
SELECT t.name, COUNT(*) FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.name;

/* Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
SELECT p.*, t.name FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';

/*Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук*/
SELECT t.name, COUNT(*) AS "Количество продуктов" FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(*) < 10;

/*Вывести все продукты и их тип*/
SELECT t.*, p.* FROM product p
JOIN type t ON p.type_id = t.id;



