create table interprete (
	id serial primary key,
	nome varchar(30) unique not null,
	sobre text,
	generos varchar(30)
);

CREATE TABLE album (
	id serial PRIMARY KEY,
	nome varchar(30) unique not null,
	gravadora varchar(30),
	lancamento date,
	sobre text,
	interprete bigint not null,
	foreign key (interprete) references interprete(id)
);

create table gravadora (
	id serial primary key,
	nome varchar(30),
	sobre text
);

create table musica (
	id serial primary key,
	nome varchar(50) not null,
	selo bigint,
	lancamento date,
	estilo varchar(30),
	sobre text,
	tempo varchar(10),
	intro varchar(10),
	album bigint,
	interprete bigint not null,
	unique (nome, interprete),
	foreign key (album) references musica(id) ,
	foreign key (interprete) references interprete(id), 
	foreign key (selo) references gravadora(id)
);

create table genero (
	id serial primary key,
	nome varchar(30)
)