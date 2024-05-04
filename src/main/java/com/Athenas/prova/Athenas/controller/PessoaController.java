package com.Athenas.prova.Athenas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Athenas.prova.Athenas.model.PesoIdeal;
import com.Athenas.prova.Athenas.model.Pessoa;
import com.Athenas.prova.Athenas.model.PessoaDTO;
import com.Athenas.prova.Athenas.service.PessoaService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/pessoa")
public class PessoaController {
	
	private final PessoaService service;
	
	
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> getAllPessoas(){
		
		List<PessoaDTO> listaPessoas = this.service.getPessoas();
		
		return ResponseEntity.ok(listaPessoas);
	}
	
	@GetMapping("/findByName/{nomePessoa}")
	public ResponseEntity<List<PessoaDTO>> getPessoasByName(@PathVariable("nomePessoa") String nomePessoa){
		
		List<PessoaDTO> listaPessoas = this.service.getPessoasByNome(nomePessoa);
		
		return ResponseEntity.ok(listaPessoas);
	}
	
	@GetMapping("/findById/{idPessoa}")
	public ResponseEntity<PessoaDTO> getPessoaByid(@PathVariable("idPessoa") Long idPessoa){
		
		PessoaDTO pessoaById = this.service.getPessoaById(idPessoa);
		
		return ResponseEntity.ok(pessoaById);
	}
	
	@PostMapping
	public ResponseEntity<?> addPessoa(@RequestBody PessoaDTO novaPessoa){
		
		Pessoa pessoaSalva = this.service.addNewPessoa(novaPessoa);
		
		if(pessoaSalva == null) {
			return ResponseEntity.badRequest().body("Falha ao criar Pessoa");
		}
		return ResponseEntity.created(null).body(pessoaSalva);
	}
	
	@PutMapping
	public ResponseEntity<PessoaDTO> updatePessoa(@RequestBody PessoaDTO altPessoa){
			PessoaDTO pessoaAlterada = this.service.updatePessoa(altPessoa);
			return ResponseEntity.ok(pessoaAlterada);
	}
	
	@DeleteMapping(path = "/delete/{idPessoa}")
	public ResponseEntity<?> deletePessoa(@PathVariable("idPessoa") Long idPessoa){
		this.service.deletePessoa(idPessoa);
		
		return ResponseEntity.ok().body("{\"message\":\"Pessoa deletada com sucesso\"}");
	}
	
	@GetMapping("/PesoIdeal/{idPessoa}")
	public ResponseEntity<?> calcularPesoIdeal(@PathVariable("idPessoa") Long idPessoa){
		
		PesoIdeal pesoIdealPessoa = this.service.getPesoIdeal(idPessoa);
		
		return ResponseEntity.ok(pesoIdealPessoa);
	}
	


}
