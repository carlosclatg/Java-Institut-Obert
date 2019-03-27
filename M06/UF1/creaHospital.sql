CREATE TYPE dades_centre as (nom character varying(30), pressupost real, adreça character varying(40));


CREATE TABLE Hospital
(
  id integer NOT NULL,
  director character varying(30),
  hospital dades_centre,
  especialitats character varying(25)[],
  CONSTRAINT clauPrimariaHospital PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE Hospital
  OWNER TO ioc;
