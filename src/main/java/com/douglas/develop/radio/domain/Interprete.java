package com.douglas.develop.radio.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Interprete extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String sobre;
	
	private String generos;
	
	private List<Album> albuns = new ArrayList<>();
	
	private List<Musica> musicas = new ArrayList<Musica>();
}

