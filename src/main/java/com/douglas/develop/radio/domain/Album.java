package com.douglas.develop.radio.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String gravadora;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate lancamento;
	
	private String sobre;
	
	private Interprete interprete;
	
	private List<Musica> musicas = new ArrayList<Musica>();

}
