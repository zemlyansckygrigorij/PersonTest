-- Database: person_test



CREATE TABLE IF NOT EXISTS public.person
(
    id integer NOT NULL DEFAULT nextval('person_id_seq'::regclass),
    data jsonb,
    CONSTRAINT person_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;