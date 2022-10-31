-- Database: person_test

-- DROP DATABASE person_test;

CREATE DATABASE person_test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- DROP TABLE public.person;

CREATE TABLE public.person
(
    id integer NOT NULL DEFAULT nextval('person_id_seq'::regclass),
    data jsonb,
    CONSTRAINT person_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;