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
import com.douglas.develop.radio.domain.Album;
import com.douglas.develop.radio.domain.Interprete;
import com.douglas.develop.radio.feignclients.RadioFeign;

@Service
public class AlbumService {
	
	@Autowired
	private RadioFeign repository;
	@Autowired
	private Datatables dataTables; 
	
	@Transactional(readOnly = false)
	public void salvar(Album album) {
		if (album.getInterprete().hasNotId()) {
			Interprete interprete = new Interprete();
			interprete.setId(1L);
			album.setInterprete(interprete);
		}
		repository.insert(album);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> buscarAlbuns(HttpServletRequest request) {
		repository.stautus();
		List<Album> lista = repository.findAll().getBody();
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.ALBUNS);
		Page<?> page = new PageImpl<>(lista);
		return dataTables.getResponse(page);
	}//Page<Something> page = new PageImpl<>(yourList);

	@Transactional(readOnly = true)
	public Album buscarPorId(Long id) {
		return repository.findbyId(id).getBody();
	}

	public Map<String, Object> lista() {
		List<Album> lista = (List<Album>) repository.findAll();
		Page<?> page = new PageImpl<>(lista);
		return dataTables.getResponse(page);
	}
}