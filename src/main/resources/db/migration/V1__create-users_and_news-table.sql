CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY NOT NULL,
                       name VARCHAR(60),
                       age INT,
                       birth_date DATE,
                       cpf VARCHAR(11),
                       rg VARCHAR(9),
                       email VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE news (
                      id BIGSERIAL PRIMARY KEY NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      subtitle VARCHAR(255),
                      content TEXT NOT NULL,
                      city VARCHAR(30) NOT NULL,
                      country VARCHAR(30) NOT NULL,
                      created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                      last_update TIMESTAMP,
                      user_id BIGINT NOT NULL,
                      CONSTRAINT fk_news_user FOREIGN KEY (user_id) REFERENCES users(id)
);
