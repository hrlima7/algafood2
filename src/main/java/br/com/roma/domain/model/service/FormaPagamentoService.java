package br.com.roma.domain.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.roma.domain.model.FormaPagamento;
import br.com.roma.domain.model.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	FormaPagamentoRepository  formaPagamentoRepository;
	
	public List<FormaPagamento> listar(){
			return formaPagamentoRepository.findAll();

	}

	public FormaPagamento salvar(FormaPagamento formaPagamento) {
			return	formaPagamentoRepository.save(formaPagamento);
	
	}
	
	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId).
				orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}
	
	public void deletar(Long formaPagamentoid) {
		
		FormaPagamento pagamentoDeletar = formaPagamentoRepository.findById(formaPagamentoid).
				orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoid));
	
		formaPagamentoRepository.delete(pagamentoDeletar);
		
	}
	
	
	

}
