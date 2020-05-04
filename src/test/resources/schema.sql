CREATE USER techtalk WITH ENCRYPTED PASSWORD 'techtalk';
CREATE SCHEMA AUTHORIZATION techtalk;
CREATE SCHEMA IF NOT EXISTS api AUTHORIZATION techtalk;
    CREATE TABLE person(
        id SERIAL NOT NULL,
        fullName VARCHAR NULL,
        nickname VARCHAR NULL,
        role VARCHAR NULL,
        createdAt TIMESTAMP NOT NULL,
        updatedAt TIMESTAMP NOT NULL,
        createdBy INTEGER NOT NULL,
        updatedBy INTEGER NOT NULL
    )