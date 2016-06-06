DROP TABLE if EXISTS person CASCADE;
  
CREATE TABLE person
(
  id bigserial NOT NULL,
  first_name character varying(255),
  middle_name character varying(255),
  last_name character varying(255),
  birthday date,
  gwa real,
  date_hired date,
  is_employed boolean,
  gender character varying(255),
  CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person OWNER TO postgres;
