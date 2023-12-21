package com.douglas.develop.radio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.develop.radio.datatables.Datatables;
import com.douglas.develop.radio.datatables.DatatablesColunas;
import com.douglas.develop.radio.domain.Agendamento;
import com.douglas.develop.radio.domain.Horario;
import com.douglas.develop.radio.exception.AcessoNegadoException;
import com.douglas.develop.radio.repository.AgendamentoRepository;
import com.douglas.develop.radio.repository.projection.HistoricoPaciente;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AgendamentoService {

	private final  AgendamentoRepository repository;
	
	private final Datatables datatables;
	
	@Transactional(readOnly = true)
	public List<Horario> buscarHorariosNaoAgendadosPorMedicoIdEData(Long id, LocalDate data) {
		return repository.findByMedicoIdAndDataNotHorarioAgendado(id, data);
	}

	@Transactional(readOnly = false)
	public void salvar(Agendamento agendamento) {
		repository.save(agendamento);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarHistoricoPorPacienteEmail(String email, HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.AGENDAMENTOS);
		Page<HistoricoPaciente> page = repository.findHistoricoByPacienteEmail(email, datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarHistoricoPorMedicoEmail(String email, HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.AGENDAMENTOS);
		Page<HistoricoPaciente> page = repository.findHistoricoBymedicoEmail(email, datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Agendamento buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void editar(Agendamento agendamento, String email) {
		Agendamento ag = buscarPorIdEUsuario(agendamento.getId(), email);
		ag.setDataConsulta(agendamento.getDataConsulta());
		ag.setEspecialidade(agendamento.getEspecialidade());
		ag.setHorario(agendamento.getHorario());
		ag.setMedico(agendamento.getMedico());
	}

	@Transactional(readOnly = true)
	public Agendamento buscarPorIdEUsuario(Long id, String email) {
		
		return repository.findByIdAndPacienteOrMedico(id, email)
				.orElseThrow(() -> new AcessoNegadoException("Acesso negado ao usuario " + email));
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		repository.deleteById(id);
	}

	
	
}
