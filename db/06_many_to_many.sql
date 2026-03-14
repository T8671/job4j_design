CREATE TABLE quests(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE rewards(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE quests_rewards(
    id SERIAL PRIMARY KEY,
    quest_id INT REFERENCES quests(id),
    reward_id INT REFERENCES rewards(id)
);
