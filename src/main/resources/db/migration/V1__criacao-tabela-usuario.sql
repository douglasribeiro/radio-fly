CREATE TABLE usuarios (
  id serial PRIMARY KEY,
  ativo boolean default FALSE,
  email varchar(150) UNIQUE NOT NULL,
  senha varchar(255) NOT NULL,
  codigo_verificador varchar(6)
  );
  
CREATE TABLE  perfis (
	id serial PRIMARY KEY,
	descricao VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE usuarios_tem_perfis (
	usuario_id bigint,
	perfil_id bigint,
	CONSTRAINT fk_id_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
	CONSTRAINT fk_id_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

INSERT INTO perfis (id, descricao) values (1, 'ADMIN');
INSERT INTO perfis (id, descricao) values (2, 'MEDICO');
INSERT INTO perfis (id, descricao) values (3, 'PACIENTE');

INSERT INTO usuarios (id, ativo, email, senha, codigo_verificador) values (1, true, 'admin@clinica.com.br', '$2a$10$UezBgN0IT1JWdEq/gTPUducN0gGs5rwBUWxI6qymaoYzm0pgJi2.y', null);

INSERT INTO usuarios_tem_perfis (usuario_id, perfil_id) values (1,1)