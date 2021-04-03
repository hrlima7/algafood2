package br.com.roma.api.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.roma.domain.model.FormaPagamento;
import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.repository.FormaPagamentoRepository;
import br.com.roma.domain.model.service.FormaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FormaPagamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	FormaPagamentoService formaPagamentoService;
	
	@Autowired
	FormaPagamentoRepository formaPagamentoRepository;

	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoService.listar();
		
	}
	
	@GetMapping("/{pagamentoId}")
	@ResponseStatus(HttpStatus.ACCEPTED.OK)
	public FormaPagamento buscar(@PathVariable Long pagamentoId) {
		return formaPagamentoService.buscarOuFalhar(pagamentoId);
	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento salvar(@RequestBody FormaPagamento formaPagamento) {
		return formaPagamentoService.salvar(formaPagamento);
	}
	
	@PutMapping("/{FormapagamentoId}")
	public FormaPagamento atualizar(@RequestBody FormaPagamento formaPagamento, 
									@PathVariable Long FormapagamentoId) {
	
		FormaPagamento formaPagamentoVelho = formaPagamentoService.buscarOuFalhar(FormapagamentoId);
		BeanUtils.copyProperties(formaPagamento,formaPagamentoVelho,"id");
		
		System.out.println(formaPagamento.toString());
		System.out.println(formaPagamentoVelho.toString());
	
		return	formaPagamentoService.salvar(formaPagamentoVelho);	
			
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.OK)
	public void deletar (@PathVariable Long formaPagamentoId ) {	
		formaPagamentoService.deletar(formaPagamentoId);
		
	}
	
	
	
	
}
