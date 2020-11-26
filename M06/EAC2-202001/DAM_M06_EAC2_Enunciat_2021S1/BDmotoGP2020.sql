CREATE TYPE pilot as
	(
		matricula character varying(10),
		nomPilot character varying(40),
		kg  integer,
		llocNaixement character varying(25)
	);


CREATE TABLE Equip
	(
		id integer NOT NULL,
		nom character varying(30),
		seu character varying(40),
		fundada integer,
  		corredor pilot,
		podis character varying(40)[],
  		CONSTRAINT clauPrimariaEquip PRIMARY KEY (id)
	)
	WITH (
  		OIDS=FALSE
	);


ALTER TABLE Equip
	OWNER TO ioc;

