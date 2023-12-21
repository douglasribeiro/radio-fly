package com.douglas.develop.radio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genero extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;

}
