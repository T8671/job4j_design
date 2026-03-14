CREATE TABLE skills(
    id SERIAL PRIMARY KEY,
    description TEXT,
    hero_id INT REFERENCES heroes(id) UNIQUE
);

CREATE TABLE heroes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    skills_id INT REFERENCES skills(id) UNIQUE
);