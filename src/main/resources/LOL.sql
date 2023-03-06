CREATE TABLE campeones (
id_campeones serial,
nombre varchar(600),
popularidad varchar(600),
porcentaje_de_victoria varchar(600),
porcentaje_de_baneo varchar(600),
kda varchar(600),
pentas_por_partida varchar(600),
primary key(id_campeones));



CREATE TABLE hechizos (
id_hechizos serial,
nombre varchar(500),
popularidad varchar(500),
porcentaje_de_victoria varchar(500),
primary key(id_hechizos));



CREATE TABLE objetos (
id_objetos serial,
popularidad varchar(200),
porcentaje_de_victoria varchar(200),
primary key(id_objetos));