CREATE TABLE branch_forces(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE heroes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    branch_forces_id INT REFERENCES branch_forces(id)
);