package com.douglas.develop.radio.datatables;

public class DatatablesColunas {
	
	private DatatablesColunas() {}

	public static final String[] ESPECIALIDADES = {"id", "titulo"};

	public static final String[] MEDICOS = {"id", "nome", "crm", "dtInscricao", "especialidades"};
	
	public static final String[] AGENDAMENTOS = {"id", "paciente.nome", "dataConsulta", "medico.nome", "especialidade.titulo"};

	public static final String[] USUARIOS = {"id", "email", "ativo", "perfis"};
	
	public static final String[] ALBUNS = {"id", "nome", "gravadora",  "lancamento", "sobre", "interprete.nome"};
	
	public static final String[] INTERPRETES = {"id", "nome", "sobre", "generos"};
}
