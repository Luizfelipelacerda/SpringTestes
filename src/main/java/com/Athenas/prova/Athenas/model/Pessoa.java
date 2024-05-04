package com.Athenas.prova.Athenas.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPessoa;
	private String nome;
	private Date data_Nasc;
	private String cpf;
	private char sexo;
	private Double altura;
	private int peso;
	
	
	

}
