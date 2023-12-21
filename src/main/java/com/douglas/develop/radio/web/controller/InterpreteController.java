package com.douglas.develop.radio.web.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.douglas.develop.radio.domain.Interprete;
import com.douglas.develop.radio.service.InterpreteService;


@Controller
@RequestMapping("interpretes")
public class InterpreteController {
	
	@Autowired
	private InterpreteService service;

	@GetMapping({"", "/"})
	public String abrir(Interprete interprete) {
		return "interprete/interprete";
	}
	
	@GetMapping({"/datatables/server"})
	public ResponseEntity<?> getInterpretes(HttpServletRequest request) {
		Map<String, Object> lista = service.lista(request);  //.buscarAlbuns(request);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/salvar")
	public String salvar(Interprete interprete, RedirectAttributes attr) {
		service.salvar(interprete);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
		return "redirect:/interpretes";
	}
	
	@GetMapping({"/editar/{id}"})
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("interprete", service.buscarPorId(id));
		return "interprete/interprete";
	}
	
	@GetMapping({"/excluir/{id}"})
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.remover(id);
		attr.addAttribute("sucesso", "Opertação realizada com sucesso.");
		return "redirect:/interpretes";
	}
	
	@GetMapping({"/nome"})
	public List<Interprete> findInterpreteForNome(@RequestParam("termo") String termo) { 
		List<Interprete> lista = service.buscaPorNome(termo);
		return lista;
	}
}
