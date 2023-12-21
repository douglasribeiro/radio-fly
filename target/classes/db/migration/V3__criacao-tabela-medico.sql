CREATE TABLE medicos (
  id serial primary key,
  crm int unique NOT NULL,
  data_inscricao date NOT NULL,
  nome varchar(70) unique NOT NULL,
  id_usuario bigint unique DEFAULT NULL,
  CONSTRAINT FK_USUARIO_ID FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE medicos_tem_especialidades (
  id_especialidade bigint not null ,
  id_medico bigint NOT NULL,
  UNIQUE (id_especialidade,id_medico),
  primary KEY (id_medico),
  CONSTRAINT FK_ESPECIALIDADE_MEDICO_ID FOREIGN KEY (id_medico) REFERENCES medicos(id),
  CONSTRAINT FK_MEDICO_ESPECIALIDADE_ID FOREIGN KEY (id_especialidade) REFERENCES especialidades(id)
);