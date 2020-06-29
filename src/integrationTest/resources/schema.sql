CREATE SCHEMA IF NOT EXISTS public;
CREATE TABLE public.person(
    id SERIAL NOT NULL,
    full_name VARCHAR NULL,
    nickname VARCHAR NULL,
    role VARCHAR NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by INTEGER NOT NULL,
    updated_by INTEGER NOT NULL
)