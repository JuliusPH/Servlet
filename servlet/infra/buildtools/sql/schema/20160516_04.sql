DROP TABLE if EXISTS role CASCADE;

CREATE TABLE role
(
  id bigserial NOT NULL,
  value character varying(255),
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role
  OWNER TO postgres;
  
INSERT INTO role VALUES(1, 'President');
INSERT INTO role VALUES(2, 'Project Manager');
INSERT INTO role VALUES(3, 'QA Tester');
INSERT INTO role VALUES(4, 'Developer');
INSERT INTO role VALUES(5, 'Trainor');
INSERT INTO role VALUES(6, 'Trainee');
