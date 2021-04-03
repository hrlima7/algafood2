package br.com.roma.domain.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.CidadeNaoEncontradaException;
import br.com.roma.domain.exception.EntidadeEmUsoException;
import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.model.Cidade;
import br.com.roma.domain.model.Estado;
import br.com.roma.domain.model.repository.CidadeRepository;

@Service
public class CidadeService {
	
	private static final String MSG_CIDADE_EM_USO 
	= "Cidade de código %d não pode ser removida, pois está em uso";

private static final String MSG_CIDADE_NAO_ENCONTRADA 
	= "Não existe um cadastro de cidade com código %d aqui";

@Autowired
private CidadeRepository cidadeRepository;

@Autowired
private EstadoService cadastroEstado;

public List<Cidade> buscarTodos() {
	return cidadeRepository.findAll();
}

public Cidade salvar(Cidade cidade) {
	Long estadoId = cidade.getEstado().getId();

	Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
	

	
 	//Optional<Estado> estado1 = estadoRepository.findById(estadoId)
	//.orElseThrow(() -> new EntidadeNaoEncontradaException(
	//			String.format("Não existe cadastro de estado com código %d", estadoId)));
	
	cidade.setEstado(estado);
	
	return cidadeRepository.save(cidade);
}

public void excluir(Long cidadeId) {
	try {
		cidadeRepository.deleteById(cidadeId);
		
	} catch (EmptyResultDataAccessException e) {
		throw new CidadeNaoEncontradaException(cidadeId);
	}
	catch (DataIntegrityViolationException e) {
		throw new EntidadeEmUsoException(
			String.format(MSG_CIDADE_EM_USO, cidadeId));
	}
}

public Cidade buscarOuFalhar(Long cidadeId) {
	return cidadeRepository.findById(cidadeId)
		.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
}

}