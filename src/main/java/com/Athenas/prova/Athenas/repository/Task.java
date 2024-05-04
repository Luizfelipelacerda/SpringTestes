package com.Athenas.prova.Athenas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Athenas.prova.Athenas.model.Pessoa;


@Repository
public interface Task 
	extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findAllByNomeContaining(String nome);
	

}
