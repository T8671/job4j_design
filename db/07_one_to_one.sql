CREATE TABLE skills(
    id SERIAL PRIMARY KEY,
    description TEXT
);

CREATE TABLE heroes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE skills_heroes(
    id SERIAL PRIMARY KEY,
    skills_id INT REFERENCES skills(id) UNIQUE,
    heroes_id INT REFERENCES heroes(id) UNIQUE
);