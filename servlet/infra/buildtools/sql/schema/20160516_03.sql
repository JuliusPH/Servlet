DROP TABLE if EXISTS contact CASCADE;

CREATE TABLE contact
(
  id bigserial NOT NULL,
  contact_type character varying(255),
  value character varying(255),
  person_id bigint,
  CONSTRAINT contact_pkey PRIMARY KEY (id),
  CONSTRAINT person_fkey FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact OWNER TO postgres;
