package com.douglas.develop.radio.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.develop.radio.datatables.Datatables;
import com.douglas.develop.radio.datatables.DatatablesColunas;
import com.douglas.develop.radio.domain.Interprete;
import com.douglas.develop.radio.feignclients.InterpreteFeign;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InterpreteService {
	
	@Autowired
	private InterpreteFeign repository;
	@Autowired
	private Datatables dataTables; 

	@Transactional(readOnly = false)
	public void salvar(Interprete interprete) {
		repository.insert(interprete);
	}
	
	@Transactional(readOnly = true)
	public Interprete buscarPorId(Long id) {
		return repository.findbyId(id).getBody();
	}

	public Map<String, Object> lista(HttpServletRequest request) {
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.INTERPRETES);
		List<Interprete> lista = repository.findAll().getBody();
		Page<?> page = new PageImpl<>(lista);
		return dataTables.getResponse(page);
	}
	
	@Transactional(readOnly = true)
	public void remover(Long id) {
		repository.delete(id);
	}

	@Transactional(readOnly = true)
	public List<Interprete> buscaPorNome(String nome) {
		log.info("Pesquisa interprete {}", nome);
		List<Interprete> obj = repository.findByNome(nome);
		return obj;
	}

}
