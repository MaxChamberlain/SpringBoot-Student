CREATE TABLE IF NOT EXISTS public._user
(
    id bigint NOT NULL,
    allow_emails boolean,
    allow_texts boolean,
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    student_first_name character varying(255) COLLATE pg_catalog."default",
    student_last_name character varying(255) COLLATE pg_catalog."default",
    student_phone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT _user_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public._user
    OWNER to postgres;