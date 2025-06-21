CREATE TABLE movies
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    director     VARCHAR(255),
    actors       TEXT,
    release      INT,
    description  TEXT,
    rating       DECIMAL(3, 1),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_Trending  BOOLEAN   DEFAULT FALSE
);
