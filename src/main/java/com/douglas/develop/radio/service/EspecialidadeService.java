package com.douglas.develop.radio.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.develop.radio.datatables.Datatables;
import com.douglas.develop.radio.datatables.DatatablesColunas;
import com.douglas.develop.radio.domain.Especialidade;
import com.douglas.develop.radio.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository repository;
	@Autowired
	private Datatables dataTables;
	
	@Transactional(readOnly = false)
	public void salvar(Especialidade especialidade) {
		repository.save(especialidade);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarEspecialidades(HttpServletRequest request) {
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.ESPECIALIDADES);
		Page<?> page = dataTables.getSearch().isEmpty()
				? repository.findAll(dataTables.getPageable())
				: repository.findAllByTitulo(dataTables.getSearch(), dataTables.getPageable());
		return dataTables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Object buscarPorId(Long id) {
		return repository.findById(id).get();	}

	@Transactional(readOnly = true)
	public void remover(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<String> buscarEspecialidadesByTermo(String termo) {
		return repository.findByEspecialidadesByTermo(termo);
	}

	@Transactional(readOnly = true)
	public Set<Especialidade> buscarPorTitulos(String[] titulos) {
		
		return repository.findByTitulos(titulos);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarEspecialidadesPorMedico(Long id, HttpServletRequest request) {
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.ESPECIALIDADES);
		Page<Especialidade> page = repository.findByIdMedico(id, dataTables.getPageable());
		return dataTables.getResponse(page);
	}
	
}
