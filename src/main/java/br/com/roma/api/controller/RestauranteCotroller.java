package br.com.roma.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.roma.api.model.CozinhaModelDTO;
import br.com.roma.api.model.RestauranteModelDTO;
import br.com.roma.api.model.input.RestauranteInputDTO;
import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.exception.NegocioException;
import br.com.roma.domain.exception.RestauranteNaoEncontradoException;
import br.com.roma.domain.model.Cozinha;
import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.service.RestauranteCadastroService;
import br.com.roma.domain.model.service.UsuarioService;
import br.com.roma.domain.model.view.RestauranteView;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteCotroller {
	
	@Autowired
	RestauranteCadastroService restauranteService;
	
	@Autowired
	UsuarioService usuarioService;
	

//	@GetMapping
//	public List<RestauranteModelDTO> listar(){
//		return toCollectionModel(restauranteService.listar());
//	}
	
		@GetMapping
		public MappingJacksonValue listar(@RequestParam(required = false) String projecaoTeste){
			
	 List<RestauranteModelDTO> restaurante =  toCollectionModel(restauranteService.listar());
		MappingJacksonValue restauranteWrapper = new MappingJacksonValue(restaurante);
				
			restauranteWrapper.setSerializationView(RestauranteView.Resumo.class);
			
			if("apenas-nome".equalsIgnoreCase(projecaoTeste)) {
			restauranteWrapper.setSerializationView(RestauranteView.ApenasNome.class);
			
			}else if("completo".equals(projecaoTeste)) {
				restauranteWrapper.setSerializationView(null);
			}
				
				return restauranteWrapper;
	}
	
	
	
	
//	@JsonView(RestauranteView.Resumo.class)
//	@GetMapping(params ="projecao=resumo" )
//	public List<RestauranteModelDTO> listarResumo(){
	//	return toCollectionModel(restauranteService.listar());
//	}
	
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params ="projecao=apenasNome" )
	public List<RestauranteModelDTO> listarApenasNome(){
		return toCollectionModel(restauranteService.listar());
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteModelDTO buscar (@PathVariable Long restauranteId) {			
		
			Restaurante restaurante = restauranteService.buscar(restauranteId);							
			return toModel(restaurante);		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModelDTO salvar(@RequestBody @Valid RestauranteInputDTO restauranteInput){
		
		try {
			Restaurante restaurante = toDomainObject(restauranteInput);
			return  toModel(restauranteService.salvar(restaurante));
		
		}catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}	
		catch (NullPointerException e) {
			throw new NullPointerException( e.getMessage());
		}		
	}
	@PutMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, 
			@RequestBody Restaurante restaurante){
			
	    Restaurante restauranteAtual = restauranteService.buscar(restauranteId);
		
	    if(restauranteAtual != null) {
	    //	restauranteAtual.setNome(restaurante.getNome());
	    //	restauranteAtual.setTaxaFrete(restaurante.getTaxaFrete());
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamentos","dataCadastro"); //outra forma de editar
	
	    	restauranteAtual.setCozinha((restaurante.getCozinha()));
	    	restauranteService.salvar(restauranteAtual);
	    
	    	return ResponseEntity.ok(restauranteAtual);
	    }	
	    return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<?> excluir(@PathVariable Long restauranteId){
		
		Restaurante restaurante = restauranteService.buscar(restauranteId);	
		try {		
			restauranteService.excluir(restaurante);
				return ResponseEntity.noContent().build();
		
		}catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	private RestauranteModelDTO toModel(Restaurante restaurante) {
		RestauranteModelDTO restauranteModelDTO = new RestauranteModelDTO();
		CozinhaModelDTO cozinhaModelDTO = new CozinhaModelDTO();
		
		//cozinhaModelDTO.setId(restaurante.getCozinha().getId());
		//cozinhaModelDTO.setNome(restaurante.getCozinha().getNome());
			
			restauranteModelDTO.setId(restaurante.getId());
			restauranteModelDTO.setNome(restaurante.getNome());
			restauranteModelDTO.setAtivo(restaurante.getAtivo());
		 	restauranteModelDTO.setTaxaFrete(restaurante.getTaxaFrete());
			restauranteModelDTO.setCozinha(cozinhaModelDTO);
			restauranteModelDTO.setAberto(restaurante.getAberto());;
		return restauranteModelDTO;
	}
	
	private List<RestauranteModelDTO> toCollectionModel(List<Restaurante> restaurantes ){
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}

	private Restaurante toDomainObject(RestauranteInputDTO restauranteInput) {
		
		Restaurante restaurante  =  new Restaurante();	
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		restaurante.setAberto(restauranteInput.getAberto());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinhaId().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante;
	
	}
	
	//PUT /restaurantes/{id}/ativo
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
			restauranteService.ativar(restauranteId);
		
	}
	//DELETE restaurantes/{id}/ativo
	
	@DeleteMapping("/{inativarId}/inativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long inativarId) {
		restauranteService.inativar(inativarId);
	}	
	
	//ATIVACAO RESTAURANTES EM MASSA
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restaurantesIds) {
		try {
		restauranteService.ativar(restaurantesIds);
		}	catch (RestauranteNaoEncontradoException e) {
		}
		
	}
	
	//INATIVACAO RESTAURANTES EM MASSA
	@DeleteMapping("/inativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restaurantesIds) {
		try {
			restauranteService.inativar(restaurantesIds);
			}	catch (RestauranteNaoEncontradoException e) {
			}
	}

	@PutMapping("/{restauranteId}/abrir")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrirRestaurante(@PathVariable Long restauranteId) {
		restauranteService.abrirRestaurante(restauranteId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/fechar")
	public void fecharRestaurante(@PathVariable Long restauranteId) {
		restauranteService.fecharRestaurante(restauranteId);
	}
	
	
	
	

}









