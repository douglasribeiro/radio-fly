package com.douglas.develop.radio.web.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.douglas.develop.radio.domain.Album;
import com.douglas.develop.radio.service.AlbumService;

@Controller
@RequestMapping("albuns")
public class AlbunsController {

	@Autowired
	private AlbumService service;
	
	@GetMapping({"", "/"})
	public String abrir(Album album) {
		return "album/album";
	}
	
	@PostMapping("/salvar")
	public String salvar(Album album, RedirectAttributes attr) {
		service.salvar(album);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/albuns";
	}
		
	@GetMapping({"/datatables/server"})
	public ResponseEntity<?> getAlbuns(HttpServletRequest request) {
		Map<String, Object> lista = service.buscarAlbuns(request);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping({"/editar/{id}"})
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("album", service.buscarPorId(id));
		return "album/album";
	}
	
	@GetMapping({"/excluir/{id}"})
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		//service.remover(id);
		//attr.addAttribute("sucesso", "Opertação realizada com sucesso.");
		attr.addAttribute("sucesso", "Opertação de Exclusão não foi implementada.");
		return "redirect:/albuns";
	}
	
//	@GetMapping({"/nome"})
//	public ResponseEntity<?> getEspecialidadesPorTermo(@RequestParam("termo") String termo) {
//		List<String> especialidades = service.buscarEspecialidadesByTermo(termo);
//		return ResponseEntity.ok(especialidades);
//	}
	
//	@GetMapping("/datatables/server/medico/{id}")
//	public ResponseEntity<?> getEspecialidadesPorMedico(@PathVariable("id") Long id, HttpServletRequest request) {		
//		return ResponseEntity.ok(service.buscarEspecialidadesPorMedico(id, request));
//	}
}
