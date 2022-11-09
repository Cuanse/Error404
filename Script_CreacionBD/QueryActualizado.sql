create database proyecto;
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
   ID_PERFIL            int,
   PASSWORD             varchar(32) not null,
   NOMBREUSUARIO        varchar(32) not null,
   ESADMIN              bool not null default false,
   ESACTIVO             bool not null default true,
   CARRERA				varchar(32),
   CREADO               TIMESTAMP default NOW(),
   primary key (ID_USUARIO)
);

create table RECUPERARCONTRA
(
   ID_SOLICITUD         int not null,
   ID_USUARIO           int not null,
   CODIGO               varchar(50) not null,
   SOLICITUDCRE         datetime,
   primary key (ID_SOLICITUD)
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
   CUMPLEANOS           date,
   GENERO               varchar(1) not null,
   BIOGRAFIA            text(1500),
   TELEFONO             varchar(20) not null,
   DIRECCION            varchar(60),
   EMAILPUBLICO         varchar(60) not null,
   primary key (ID_PERFIL)
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
   ID_PERFIL            int not null,
   ID_FORO              int not null,
   CONTENIDO            text(1500) not null,
   TITULO               varchar(32) not null,
   ENABLED				bool not null default false,
   CREADOPOST           TIMESTAMP not null default NOW(),
   primary key (ID_POST)
);
create table NOTICIA
(
   ID_NOTICIA           int not null,
   primary key (ID_NOTICIA)
);
create table EVENTO
(
   ID_EVENTO            int not null,
   ID_USUARIO           int not null,
   ID_NOTICIA           int,
   FECHA                date not null,
   NOMBRE               varchar(32) not null,
   LUGAR                varchar(32) not null,
   CREADOEVENTOS        datetime,
   primary key (ID_EVENTO)
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
   ID_FORO            	int not null auto_increment,
   FECHA                timestamp default NOW(),
   NOMBRE               varchar(32) not null,
   CATEGORIA 			varchar(32) not null,
   primary key (ID_FORO)
);
