reate database proyecto;
use proyecto;
select * from user;
/*
SELECT sysdate() 'Current TIME using SYSDATE()';
alter table USUARIO modify column CREADO TIMESTAMP default NOW();
alter table USUARIO drop column email;
alter table USUARIO drop column APELLIDOUSUARIO;
alter table USUARIO modify column ESADMIN bool not null default false;
alter table USUARIO modify column ESACTIVO bool not null default true;
alter table USUARIO add column CARRERA varchar(32);
alter table USUARIO modify column ID_PERFIL int;
alter table USUARIO modify ID_USUARIO int not null AUTO_INCREMENT;
INSERT INTO USUARIO (NOMBREUSUARIO,PASSWORD,CARRERA) values ('test','test','test');*/
select * from USUARIO where ID_USUARIO = 3;
create table USUARIO
(
   ID_USUARIO           int not null AUTO_INCREMENT,
   ID_PERFIL            int not null AUTO_INCREMENT,
   PASSWORD             varchar(32) not null,
   NOMBREUSUARIO        varchar(32) not null,
   ESADMIN              bool not null default false,
   ESACTIVO             bool not null default true,
   CARRERA				varchar(32),
   CREADO               TIMESTAMP default NOW(),
   primary key (ID_USUARIO)
   /*,FOREIGN KEY (ID_PERFIL) REFERENCES PERFIL(ID_PERFIL)*/	
);

create table RECUPERARCONTRA
(
   ID_SOLICITUD         int not null AUTO_INCREMENT,
   ID_USUARIO           int not null AUTO_INCREMENT,
   CODIGO               varchar(50) not null,
   SOLICITUDCRE         TIMESTAMP not null default NOW(),
   primary key (ID_SOLICITUD)
   /*,FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)*/	
);
create table PAIS
(
   ID_PAIS              varchar(3) not null,
   NOMBREPAIS           varchar(30) not null,
   primary key (ID_PAIS)
);
/*alter table PERFIL modify column ID_PAIS varchar(3);
alter table PERFIL modify column CUMPLEANOS date;
SELECT ID_PERFIL from PERFIL;
alter table PERFIL modify column TELEFONO varchar(20) not null;
alter table PERFIL modify column ID_PERFIL int not null auto_increment;
select * from perfil;
truncate table PERFIL;
SELECT ID_PERFIL from PERFIL;
select * from usuario;
UPDATE USUARIO SET ID_PERFIL = null WHERE ID_USUARIO = 3;*/
create table PERFIL
(
   ID_PERFIL            int not null AUTO_INCREMENT,
   ID_PAIS              varchar(3),
   GENERO               varchar(1) not null,
   BIOGRAFIA            text(1500),
   TELEFONO             varchar(20) not null,
   DIRECCION            varchar(60),
   EMAILPUBLICO         varchar(60) not null,
   primary key (ID_PERFIL)
   /*,FOREIGN KEY (ID_PAIS) REFERENCES PAIS(ID_PAIS)*/	
);
/*alter table POST RENAME column ID_PERFIL to ID_USUARIO;
alter table POST modify column ID_POST int not null AUTO_INCREMENT;
alter table POST drop column ID_NOTICIA;
alter table POST modify column CREADOPOST TIMESTAMP not null default NOW();
select * from post;
SELECT * FROM POST WHERE ID_FORO = 1;
alter table POST ADD column ENABLED bool not null default false;
SELECT * FROM POST WHERE ENABLED = true and ID_FORO = 1 ORDER BY ID_POST DESC  LIMIT 10 ;
UPDATE POST SET ENABLED = 1 where ID_POST = 2;*/
select * from post;
create table POST
(
   ID_POST              int not null AUTO_INCREMENT,
   ID_PERFIL            int not null AUTO_INCREMENT, 
   ID_FORO              int not null AUTO_INCREMENT,
   CONTENIDO            text(1500) not null,
   TITULO               varchar(32) not null,
   ENABLED		bool not null default false,
   CREADOPOST           TIMESTAMP not null default NOW(),
   primary key (ID_POST)
   /*,FOREIGN KEY (ID_PERFIL) REFERENCES PERFIL(ID_PERFIL)*/	
   /*,FOREIGN KEY (ID_FORO) REFERENCES FORO(ID_FORO)*/	
);
create table NOTICIA
(
   ID_NOTICIA           int not null AUTO_INCREMENT,
   ID_POST 		int not null AUTO_INCREMENT,
   primary key (ID_NOTICIA)
   /*,FOREIGN KEY (ID_POST) REFERENCES POST(ID_POSTL)*/
);
create table EVENTO
(
   ID_EVENTO            int not null AUTO_INCREMENT,
   ID_USUARIO           int not null AUTO_INCREMENT,
   ID_NOTICIA           int not null AUTO_INCREMENT,
   FECHA                TIMESTAMP not null default NOW(),
   NOMBRE               varchar(32) not null,
   LUGAR                varchar(32) not null,
   CREADOEVENTOS        TIMESTAMP not null default NOW(),
   primary key (ID_EVENTO)
   /*,FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)*/	
   /*,FOREIGN KEY (ID_NOTICIA) REFERENCES NOTICIA(ID_NOTICIA)*/	
);
/*alter table FORO modify column FECHA timestamp default NOW();
alter table FORO drop column LUGAR;
alter table FORO modify column ID_FORO int not null auto_increment;
insert into FORO (NOMBRE,CATEGORIA) values('DEPORTES','DEPORTES');
select * from FORO;
SELECT COUNT(*) FROM FORO;
select * from FORO;
SELECT COUNT(*) from (SELECT * FROM POST WHERE ID_FORO = 0) as SUBQUERY;*/
create table FORO
(
   ID_FORO            	int not null AUTO_INCREMENT,
   FECHA                timestamp default NOW(),
   NOMBRE               varchar(32) not null,
   CATEGORIA 		varchar(32) not null,
   primary key (ID_FORO)
);
create table LIKE 
(
	ID_LIKE  		int not null AUTO_INCREMENT,
	ID_USUARIO		int not null AUTO_INCREMENT,
	primary key (ID_LIKES)
	/*,FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)*/	
);
create table COMENTARIO
(
	ID_COMENTARIO		int not null AUTO_INCREMENT,
	ID_USUARIO		int not null AUTO_INCREMENT,
	CREADOCOM        	TIMESTAMP not null default NOW(),
	CONTENIDO  		varchar(32),
	primary key (ID_COMENTARIO)
	/*,FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)*/
);

/*ID_USUARIO M SIGNIFICA ID_USUARIO MENSAJERO (o que envia la solicitud), ID_USUARIOR SIGINIFICA ID_USUARIO RECEPTOR de la solicitud
create table AMIGO
(	
	ID_AMIGO		int not null AUTO_INCREMENT,
	ID_USUARIOM		int not null AUTO_INCREMENT,
	ID_USUARIOR		int not null AUTO_INCREMENT,
	primary key (ID_AMIGO)	
);


/* BITACORA */
DROP TABLE IF EXISTS BITACORA;
create table BITACORA
(
	ID_BITACORA		int not null AUTO_INCREMENT,
	FECHA			datetime,
	TABLA			varchar(20) DEFAULT NULL,
	excutedSQL		varchar(2000) DEFAULT NULL,
	reverseSQL 		varchar(2000) DEFAULT NULL,
	primary key (ID_BITACORA)
);

/*INSERT POST TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_post;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_post
	AFTER INSERT ON POST
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"POST",
		CONCAT("INSERT INTO POST (ID_POST, ID_PERFIL, ID_FORO, CONTENIDO, TITULO, ENABLED, CREADOPOST) VALUES (",NEW.ID_POST,",",NEW.ID_PERFIL,",",NEW.ID_FORO,",""",NEW.CONTENIDO,""",""",NEW.TITULO,""",",NEW.ENABLED,",""",NEW.CREADOPOST,""");"),
		CONCAT("DELETE FROM POST WHERE ID_POST = ", NEW.ID_POST,";")
	);
END;
$$

/*INSERT USUARIO TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_usuario;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_usuario
	AFTER INSERT ON USUARIO
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"USUARIO",
		CONCAT("INSERT INTO USUARIO (ID_USUARIO, ID_PERFIL, PASSWORD, NOMBREUSUARIO, ESADMIN, ESACTIVO, CARRERA, CREADO) VALUES (",NEW.ID_USUARIO,",",NEW.ID_PERFIL,",""",NEW.PASSWORD,""",""",NEW.NOMBREUSUARIO,""",",NEW.ESADMIN,",",NEW.ESACTIVO,",""",NEW.CARRERA,""",""",NEW.CREADO,""");"),
		CONCAT("DELETE FROM USUARIO WHERE ID_USUARIO = ", NEW.ID_USUARIO,";")
	);
END;
$$

/*INSERT RECUPERARCONTRA TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_recuperarcontra;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_recuperarcontra
	AFTER INSERT ON RECUPERARCONTRA
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"RECUPERARCONTRA",
		CONCAT("INSERT INTO RECUPERARCONTRA (ID_SOLICITUD, ID_USUARIO, CODIGO, SOLICITUDCRE) VALUES (",NEW.ID_SOLICITUD,",",NEW.ID_USUARIO,",""",NEW.CODIGO,""",""",NEW.SOLICITUDCRE,""");"),
		CONCAT("DELETE FROM RECUPERARCONTRA WHERE ID_SOLICITUD = ", NEW.ID_SOLICITUD,";")
	);
END;
$$

/*INSERT PAIS TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_pais;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_pais
	AFTER INSERT ON PAIS 
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"PAIS",
		CONCAT("INSERT INTO PAIS (ID_PAIS, NOMBREPAIS) VALUES (""",NEW.ID_PAIS,""",""",NEW.NOMBREPAIS,""");"),
		CONCAT("DELETE FROM PAIS WHERE ID_PAIS = """, NEW.ID_PAIS,""";")
	);
END;
$$

/*INSERT PERFIL TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_perfil;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_perfil
	AFTER INSERT ON PERFIL
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"PERFIL",
		CONCAT("INSERT INTO PERFIL(ID_PERFIL, ID_PAIS, GENERO, BIOGRAFIA, TELEFONO, DIRECCION, EMAILPUBLICO) VALUES (",NEW.ID_PERFIL,",""",NEW.ID_PAIS,""",""",NEW.GENERO,""",""",NEW.BIOGRAFIA,""",""",NEW.TELEFONO,""",""",NEW.DIRECCION,""","""NEW.EMAILPUBLICO,""");"),
		CONCAT("DELETE FROM PERFIL WHERE ID_PERFIL = ", NEW.ID_PERFIL,";")
	);
END;
$$

/*INSERT NOTICIA TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_noticia;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_noticia
	AFTER INSERT ON NOTICIA 
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"NOTICIA",
		CONCAT("INSERT INTO NOTICIA(ID_NOTICIA, ID_POST) VALUES (",NEW.ID_PERFIL,",",NEW.ID_POST,");"),
		CONCAT("DELETE FROM NOTICIA WHERE ID_NOTICIA = ", NEW.ID_NOTICIA,";")
	);
END;
$$


/*INSERT EVENTO TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_evento;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_evento
	AFTER INSERT ON EVENTO 
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"EVENTO",
		CONCAT("INSERT INTO EVENTO (ID_EVENTO, ID_USUARIO, ID_NOTICIA, FECHA, NOMBRE, LUGAR, CREADOEVENTOS) VALUES (",NEW.ID_EVENTO,",",NEW.ID_USUARIO,",NEW.ID_NOTICIA,",""",NEW.FECHA,""",""",NEW.NOMBRE,""",""",NEW.CREADOEVENTOS,""");"),
		CONCAT("DELETE FROM EVENTO WHERE ID_EVENTO  = ", NEW.ID_EVENTO ,";")
	);
END;
$$


/*INSERT FORO TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_foro;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_foro
	AFTER INSERT ON FORO
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"EVENTO",
		CONCAT("INSERT INTO FORO(ID_FORO, FECHA, NOMBRE, CATEGORIA) VALUES (",NEW.ID_FORO,",""",NEW.FECHA,""",""",NEW.NOMBRE,""",""",NEW.FECHA,""",""",NEW.CATEGORIA,""");"),
		CONCAT("DELETE FROM FORO WHERE ID_FORO = ", NEW.ID_FORO,";")
	);
END;
$$


/*INSERT LIKE TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_like;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_like
	AFTER INSERT ON LIKE
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"LIKE",
		CONCAT("INSERT INTO LIKE(ID_LIKE, ID_USUARIO, NOMBRE) VALUES (",NEW.ID_LIKE,",",NEW.ID_USUARIO,");"),
		CONCAT("DELETE FROM LIKE WHERE ID_LIKE = ", NEW.ID_LIKE,";")
	);
END;
$$

/*INSERT COMENTARIO TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_comentario;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_comentario
	AFTER INSERT ON COMENTARIO
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"COMENTARIO",
		CONCAT("INSERT INTO COMENTARIO (ID_COMENTARIO, ID_USUARIO, CREADOCOM, CONTENIDO) VALUES (",NEW.ID_COMENTARIO,",",NEW.ID_USUARIO,",""",NEW.CREADOCOM,""",""",NEW.CONTENIDO,""");"),
		CONCAT("DELETE FROM COMENTARIO WHERE ID_COMENTARIO  = ", NEW.ID_COMENTARIO,";")
	);
END;
$$

/*INSERT AMIGO TRIGGER*/
DROP TRIGGER IF EXISTS after_insert_amigo;
DELIMITER $$ /Cambiar delimitar y retornar a estado natural hasta el end del trigger (batch)/
create TRIGGER after_insert_amigo
	AFTER INSERT ON AMIGO
	FOR EACH ROW
BEGIN
	insert into BITACORA(FECHA, TABLA, excutedSQL, reverseSQL)
	values(
		now(),"AMIGO",
		CONCAT("INSERT INTO AMIGO(ID_AMIGO, ID_USUARIOM, ID_USUARIOR) VALUES (",NEW.ID_AMIGO,",",NEW.ID_USUARIOM,",",NEW.USUARIOR,");"),
		CONCAT("DELETE FROM AMIGO WHERE ID_AMIGO  = ", NEW.ID_AMIGO,";")
	);
END;
$$