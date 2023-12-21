package com.douglas.develop.radio.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.douglas.develop.radio.domain.Album;
import com.douglas.develop.radio.domain.Interprete;

@Component
@FeignClient(name = "interprete", url = "localhost:8002", path = "/interprete")
public interface InterpreteFeign {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "status")
	String stautus();
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Interprete>> findAll();
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Interprete> findbyId(@PathVariable("id") Long id);

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> insert(@RequestBody Interprete interprete);
	
	@PutMapping(value = "/{id}")
	ResponseEntity<HttpStatus> update(@PathVariable("id") Long id, @RequestBody Interprete interprete);
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id);

//	@GetMapping(value = "/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
//	List<Interprete> findByNome(@PathVariable("nome") String nome); 
	
	@GetMapping({"/nome/{nome}"})
	List<Interprete> findByNome(@PathVariable("nome") String nome);
}

