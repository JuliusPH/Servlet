DROP TABLE if EXISTS personrole CASCADE;

CREATE TABLE personrole
(
  person_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT personrole_pkey PRIMARY KEY (person_id, role_id),
  CONSTRAINT role_fkey FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT person_fkey FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE personrole
  OWNER TO postgres;
