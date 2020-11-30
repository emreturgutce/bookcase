DROP DATABASE bookcase_db;

DROP USER bookcase;

CREATE USER bookcase WITH password 'postgres';

CREATE DATABASE bookcase_db WITH template=template0 owner=bookcase;

\connect bookcase_db;

ALTER DEFAULT PRIVILEGES GRANT ALL ON TABLES TO bookcase;

ALTER DEFAULT PRIVILEGES GRANT ALL ON SEQUENCES TO bookcase;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS books (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    author_id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE books ADD CONSTRAINT books_users_fk FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS users_books (
    PRIMARY KEY(user_id, book_id),
    user_id uuid NOT NULL,
    book_id uuid NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

ALTER TABLE users_books ADD CONSTRAINT users_books_users_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE users_books ADD CONSTRAINT users_books_books_fk FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE;