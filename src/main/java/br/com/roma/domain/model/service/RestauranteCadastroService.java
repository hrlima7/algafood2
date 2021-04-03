package br.com.roma.domain.model.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.exception.RestauranteNaoEncontradoException;
import br.com.roma.domain.model.Cozinha;
import br.com.roma.domain.model.FormaPagamento;
import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.Usuario;
import br.com.roma.domain.model.repository.CozinhaRepository;
import br.com.roma.domain.model.repository.RestauranteRepository;

@Service
public class RestauranteCadastroService {

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	FormaPagamentoService formaPagementoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired 
	CozinhaRepository cozinhaRepository;	
	
	public List<Restaurante> listar (){
		return restauranteRepository.findAll();	
	}
	
 	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).
		orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
  }
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
	//	if (!cozinha.isPresent()) {
	//		throw new CozinhaNaoEncontradaException(
	//				String.format("Não existe cadastro de cozinha de codigo %d",cozinhaId));
	//	}	
		restaurante.setCozinha(cozinha.get());
		return restauranteRepository.save(restaurante);		
		}
	
		@Transactional
		public void ativar(Long restauranteID) {
			Restaurante restauranteAtual = buscar(restauranteID);	
		    //restauranteAtual.setAtivo(true);		
			restauranteAtual.ativar();		
		}
		
		@Transactional
		public void inativar(Long restauranteID) {
			Restaurante restauranteAtual = buscar(restauranteID);			
			//restauranteAtual.setAtivo(false);			
			restauranteAtual.inativar();		
		}
		
		//ATIVAR EM LOTE
		@Transactional
		public void ativar(List<Long> restaurantesIds) {
			restaurantesIds.forEach(this::ativar);
			
		}
		//INATIVAR RESTAURANTES EM LOTE
		@Transactional
		public void inativar(List<Long> restaurantesIds) {
			restaurantesIds.forEach(this::inativar);
			
		}
		
		
		
		public void excluir(Restaurante restaurante) {
	
			try {
			restauranteRepository.delete(restaurante);
			
			}catch(EntidadeNaoEncontradaException e) {
			throw new RestauranteNaoEncontradoException(
					String.format("Nao existe um cadastro de  restaurante com código %d",restaurante.getId()));
			}	
		}
	
		@Transactional
		public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
			Restaurante restaurante = buscarOuFalhar(restauranteId);
			FormaPagamento formaPagamento = formaPagementoService.buscarOuFalhar(formaPagamentoId);
			
				restaurante.removerFormaPagamento(formaPagamento);			
			//	restaurante.getFormasPagamentos().remove(formaPagamento);	
		}
		
		@Transactional
		public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
			Restaurante restaurante = buscarOuFalhar(restauranteId);
			FormaPagamento formaPagamento = formaPagementoService.buscarOuFalhar(formaPagamentoId);
					
				restaurante.adicionarFormaPagamento(formaPagamento);
				//restaurante.getFormasPagamentos().add(formaPagamento);	
	
		}
			
		public Restaurante buscarOuFalhar(Long id) {	
			return restauranteRepository.findById(id)
					.orElseThrow(()-> new RestauranteNaoEncontradoException(id));
						//	String.format("Cozinha Não Localizada !!!", id)));	
		}
		@Transactional
		public void abrirRestaurante(Long restauranteId) {
			Restaurante restaurante = buscarOuFalhar(restauranteId);
			restaurante.aberto();
		}
		
		@Transactional
		public void fecharRestaurante(Long restauranteId) {
			Restaurante restaurante = buscarOuFalhar(restauranteId);
			restaurante.fechado();
		}
		
		public Set<Usuario> buscarResponsavel(Long restauranteId){
			Restaurante restaurante = buscarOuFalhar(restauranteId);
			
					return restaurante.getResponsaveis();
		}
		
		@Transactional
		public void associarResponsavel(Long restauranteId,Long usuarioId) {
					Restaurante restaurante = buscarOuFalhar(restauranteId);
						Usuario usuario = usuarioService.BuscarOufalhar(usuarioId);
								restaurante.associarResponsavel(usuario);
			
		}
		
		@Transactional
		public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
				Restaurante restaurante = buscarOuFalhar(restauranteId);
					Usuario usuario = usuarioService.BuscarOufalhar(usuarioId);
							restaurante.desassociarResponsavel(usuario);
		}
		
		
		
}
