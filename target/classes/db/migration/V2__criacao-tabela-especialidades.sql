CREATE TABLE especialidades (
  id serial primary key,
  descricao text,
  titulo varchar(50) unique NOT NULL
)