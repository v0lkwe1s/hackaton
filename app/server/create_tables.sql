-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  gcm_key text,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO postgres;

