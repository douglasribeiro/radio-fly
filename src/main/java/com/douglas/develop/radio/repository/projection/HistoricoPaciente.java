package com.douglas.develop.radio.repository.projection;

import com.douglas.develop.radio.domain.Especialidade;
import com.douglas.develop.radio.domain.Medico;
import com.douglas.develop.radio.domain.Paciente;

public interface HistoricoPaciente {

	Long getId();
	
	Paciente getPaciente();

	String getDataConsulta();
	
	Medico getMedico();
	
	Especialidade getEspecialidade();
}
