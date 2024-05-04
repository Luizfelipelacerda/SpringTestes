package com.Athenas.prova.Athenas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Athenas.prova.Athenas.model.PesoIdeal;
import com.Athenas.prova.Athenas.model.Pessoa;
import com.Athenas.prova.Athenas.model.PessoaDTO;
import com.Athenas.prova.Athenas.repository.Task;

import exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaService {
	
	private final Task task;
	
	public List<PessoaDTO> getPessoas() {
		List<PessoaDTO> listPessoaDTO = new ArrayList<PessoaDTO>();
		
		List<Pessoa> pessoaLista = this.task.findAll(); 
		
		pessoaLista.stream().forEach(p->{
			listPessoaDTO.add(
					PessoaDTO
						.builder()
							.idPessoa(p.getIdPessoa())
							.nome(p.getNome())
							.altura(p.getAltura())
							.cpf(p.getCpf())
							.sexo(p.getSexo())
							.peso(p.getPeso())
							.data_Nasc(p.getData_Nasc())
							.build()
					);
		});
		
		return listPessoaDTO;
	}
	
	public Pessoa addNewPessoa(PessoaDTO novaPessoaDTO) {
		Pessoa novaPessoa = Pessoa.builder()
			.nome(novaPessoaDTO.getNome())
			.altura(novaPessoaDTO.getAltura())
			.cpf(novaPessoaDTO.getCpf())
			.sexo(novaPessoaDTO.getSexo())
			.peso(novaPessoaDTO.getPeso())
			.data_Nasc(novaPessoaDTO.getData_Nasc())
			.build();
		return this.task.save(novaPessoa);
	}

	public PessoaDTO updatePessoa(PessoaDTO altPessoa) throws ResourceNotFoundException {
		
		 Optional<Pessoa> pessoaOptional = this.task.findById(altPessoa.getIdPessoa());
		 if(pessoaOptional.isEmpty()) {
			 throw new ResourceNotFoundException("Pessoa não encontrada, Id: " + altPessoa.getIdPessoa());
		 }
		 Pessoa novaPessoa = pessoaOptional.get();
		 novaPessoa.setNome(altPessoa.getNome());
		 novaPessoa.setAltura(altPessoa.getAltura());
		 novaPessoa.setCpf(altPessoa.getCpf());
		 novaPessoa.setData_Nasc(altPessoa.getData_Nasc());
		 novaPessoa.setPeso(altPessoa.getPeso());
		 novaPessoa.setSexo(altPessoa.getSexo());
		 this.task.save(novaPessoa);
		 
		 return altPessoa;
	}
	
	public void deletePessoa(Long idPessoa) {
		Optional<Pessoa> pessoaASerDeletada = this.task.findById(idPessoa);
		if(pessoaASerDeletada.isEmpty()) {
			throw new ResourceNotFoundException("Pessoa não encontrada, Id: " + idPessoa);
		}
		
		this.task.delete(pessoaASerDeletada.get());
	}

	public PessoaDTO getPessoaById(Long idPessoa) {
		Optional<Pessoa> pessoaOptional = this.task.findById(idPessoa);
		if(pessoaOptional.isEmpty()) {
			throw new ResourceNotFoundException("Pessoa não encontrada, Id: " + idPessoa);
		}
		Pessoa pessoa = pessoaOptional.get();
		PessoaDTO pessoaDTO = PessoaDTO
				.builder()
				.idPessoa(pessoa.getIdPessoa())
				.nome(pessoa.getNome())
				.cpf(pessoa.getCpf())
				.altura(pessoa.getAltura())
				.peso(pessoa.getPeso())
				.sexo(pessoa.getSexo())
				.build();
		return pessoaDTO;
		
	}

	public PesoIdeal getPesoIdeal(Long idPessoa) {
		Optional<Pessoa> pessoaOptional = this.task.findById(idPessoa);
		if(pessoaOptional.isEmpty()) {
			throw new ResourceNotFoundException("Pessoa não encontrada, Id: " + idPessoa);
		}
		Pessoa pessoa = pessoaOptional.get();
		double pesoidealCalculado = this.calculaPessoaIdeal(pessoa.getSexo(), pessoa.getAltura());
		
		PesoIdeal pesoIdeal = PesoIdeal.builder().message("O peso ideal é "+pesoidealCalculado).build();
		
		return pesoIdeal;
	}
	
	public double calculaPessoaIdeal(char sexo, Double altura) {
		double pesoIdeal;
		if(sexo == 'M') {
			pesoIdeal = 72.7*(altura*0.01);
			return Math.floor(pesoIdeal - 58);
		}else {
			pesoIdeal = 62.1*(altura*0.01);
			return Math.floor(pesoIdeal - 44.7);
		}
	}

	public List<PessoaDTO> getPessoasByNome(String nome) {
		List<PessoaDTO> pessoasListaDTO = new ArrayList<PessoaDTO>();
		List<Pessoa> pessoasLista = this.task.findAllByNomeContaining(nome);
		
		pessoasLista.stream().forEach(p -> {
			pessoasListaDTO.add(PessoaDTO
					.builder()
					.idPessoa(p.getIdPessoa())
					.nome(p.getNome())
					.cpf(p.getCpf())
					.altura(p.getAltura())
					.sexo(p.getSexo())
					.peso(p.getPeso())
					.data_Nasc(p.getData_Nasc())
					.build());
		});
		
		return pessoasListaDTO;
	}

}