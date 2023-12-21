package com.douglas.develop.radio.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Musica  extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String selo;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date lancamento;
	
	private String estilo;
	
	private String sobre;
	
	private String tempo;
	
	private String intro;
	
	private Set<Genero> generos = new HashSet<>();
	
	private Album album;
	
	private Interprete interprete;

}
