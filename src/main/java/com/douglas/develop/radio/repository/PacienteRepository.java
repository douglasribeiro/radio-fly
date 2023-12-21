package com.douglas.develop.radio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglas.develop.radio.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	@Query("select p from Paciente p where p.usuario.email like :email")
	Optional<Paciente> findByUsuarioEmail(String email);
	
}
