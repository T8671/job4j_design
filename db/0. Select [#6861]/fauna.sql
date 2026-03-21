CREATE TABLE fauna
(
    id             SERIAL PRIMARY KEY,
    name           TEXT,
    avg_age        INT,
    discovery_date DATE
);

INSERT INTO fauna (name, avg_age, discovery_date) VALUES
('Golden Fish', 15000, '1940-05-12'),
('Tiger Shark', 25000, '1980-08-21'),
('Blue Whale', 80000, NULL),
('Common Fish', 5000, '1960-03-10'),
('Red Fox', 12000, '1945-07-14'),
('Fishosaur', 18000, '1935-11-30'),
('Elephant', 60000, '1890-01-20'),
('Deep Sea Fish', 3000, '2000-09-05'),
('Mystic Fish', 20000, '1948-12-25'),
('Unknown Creature', 10000, NULL);

SELECT * FROM fauna WHERE name ILIKE '%fish%';

SELECT * FROM fauna WHERE avg_age >= 10000 AND avg_age <= 21000;

SELECT * FROM fauna WHERE  discovery_date is NULL;

SELECT * FROM fauna WHERE EXTRACT(YEAR FROM discovery_date) < 1950;