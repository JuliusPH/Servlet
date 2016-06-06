DROP TABLE if EXISTS address CASCADE;

CREATE TABLE address
(
  id bigserial NOT NULL,
  street_number character varying(255),
  barangay character varying(255),
  city character varying(255),
  zip_code character varying(255),
  CONSTRAINT address_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE address OWNER TO postgres;
