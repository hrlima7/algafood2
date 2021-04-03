package br.com.roma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.exception.EstadoNaoEncontradaException;
import br.com.roma.domain.model.Estado;
import br.com.roma.domain.model.repository.EstadoRepository;
import br.com.roma.domain.model.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();	
	}	
	
	@GetMapping("/{id}")
	public  Estado buscar(@PathVariable("id") Long estadoId) {
		return  buscarOuFalhar(estadoId);
		
		
	
	}
	
	@PostMapping
	public Estado salvar(@RequestBody Estado estado) {
		 return estadoService.salvar(estado);
	}
	
	@DeleteMapping
	public void  remover(@RequestBody Estado estado) {
		estadoService.excluir(estado.getId());
		
	}
	
	//@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
	}
	
	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
			.orElseThrow(() -> new EstadoNaoEncontradaException(estadoId));
	

}
}
