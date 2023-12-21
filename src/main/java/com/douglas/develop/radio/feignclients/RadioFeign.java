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

import com.douglas.develop.radio.config.Configuracao;
import com.douglas.develop.radio.domain.Album;

@Component
@FeignClient(name = "album", url = "localhost:8001", path = "/album")
public interface RadioFeign {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "status")
	String stautus();
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Album>> findAll();
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Album> findbyId(@PathVariable("id") Long id);

	@PostMapping
	ResponseEntity<Void> insert(@RequestBody Album album);
	
	@PutMapping(value = "/{id}")
	ResponseEntity<HttpStatus> update(@PathVariable("id") Long id, @RequestBody Album album);
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id); 
}

