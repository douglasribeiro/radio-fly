package com.douglas.develop.radio.datatables;

public class DatatablesColunas {
	
	private DatatablesColunas() {}

	protected static final String[] ESPECIALIDADES = {"id", "titulo"};

	protected static final String[] MEDICOS = {"id", "nome", "crm", "dtInscricao", "especialidades"};
	
	protected static final String[] AGENDAMENTOS = {"id", "paciente.nome", "dataConsulta", "medico.nome", "especialidade.titulo"};

	protected static final String[] USUARIOS = {"id", "email", "ativo", "perfis"};
	
	protected static final String[] ALBUNS = {"id", "nome", "gravadora",  "lancamento", "sobre", "interprete.nome"};
	
	protected static final String[] INTERPRETES = {"id", "nome", "sobre", "generos"};
}
