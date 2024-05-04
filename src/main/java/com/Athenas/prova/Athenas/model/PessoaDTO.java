package com.Athenas.prova.Athenas.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PessoaDTO {
	
	private Long idPessoa;
	private String nome;
	private Date data_Nasc;
	private String cpf;
	private char sexo;
	private Double altura;
	private int peso;
}
