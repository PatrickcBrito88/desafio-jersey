CREATE TABLE usuarios (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

INSERT INTO usuarios (login, password, role) VALUES ('user', 'senha123', 'ADMIN');