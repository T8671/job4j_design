-- create
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name TEXT,
    department_id INT REFERENCES departments(id)
);

CREATE TABLE teens (
    id SERIAL PRIMARY KEY,
    name TEXT,
    gender TEXT
);

-- insert
INSERT INTO departments(name) VALUES
  ('IT'),
  ('HR'),
  ('Finance');

INSERT INTO employees(name, department_id) VALUES
  ('Иван', 1),
  ('Мария', 1),
  ('Алексей', 2),
  ('Ольга', NULL),
  ('Сергей', 3);

INSERT INTO teens(name, gender) VALUES
  ('Артём', 'M'),
  ('Дарья', 'F'),
  ('Максим', 'M'),
  ('София', 'F'),
  ('Тимофей', 'M'),
  ('Виктория', 'F');

-- fetch

/*Выполнить запросы с left, right, full, cross соединениями*/
SELECT * FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

SELECT * FROM departments d
RIGHT JOIN  employees e ON e.department_id = d.id;

SELECT * FROM employees e
FULL JOIN departments d ON e.department_id = d.id;

SELECT * FROM employees e
CROSS JOIN departments d;

/*Используя left join найти департаменты,
у которых нет работников*/
SELECT * FROM departments d
LEFT JOIN employees e ON e.department_id = d.id
WHERE e.id IS NULL;

/*Используя left и right join написать запросы,
которые давали бы одинаковый результат
(порядок вывода колонок в эти запросах также должен быть идентичный)*/

SELECT e.id, e.name, e.department_id, d.id, d.name FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

SELECT e.id, e.name, e.department_id, d.id, d.name FROM departments d
RIGHT JOIN employees e ON e.department_id = d.id;

/*Создать таблицу teens с атрибутами name,
gender и заполнить ее.
Используя cross join составить все возможные разнополые пары.
Исключите дублирование пар вида Вася-Маша и Маша-Вася*/
SELECT t1.name, t2.name FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender != t2.gender AND t1.name < t2.name;
