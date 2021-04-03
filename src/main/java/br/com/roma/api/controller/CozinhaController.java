package br.com.roma.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.roma.domain.exception.CozinhaNaoEncontradaException;
import br.com.roma.domain.model.Cozinha;
import br.com.roma.domain.model.repository.CozinhaRepository;
import br.com.roma.domain.model.service.CadastroCozinhaService;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService ;
	
	@GetMapping
	public Page<Cozinha> listar(@PageableDefault(size = 3) Pageable pageable ){
		
		Page<Cozinha> cozinhasPage= cozinhaRepository.findAll(pageable);
		List<Cozinha>cozinhaListada =  cozinhasPage.getContent();
		Page<Cozinha> cozinhaConteudo = new PageImpl<>(cozinhaListada, pageable,cozinhasPage.getTotalElements());
	
		return cozinhaConteudo;
	}
	

	
	@GetMapping("/{id}")
	public Cozinha  buscar(@PathVariable("id") Long idCozinha) {
		return  buscarOuFalhar(idCozinha);
				
				
	//cozinhaRepository.findById(id).orElseThrow(() -> new  CozinhaNaoEncontradaException("Nao localizda!"));
				
		
	//	Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		
	//	if (cozinha.isPresent()){
			
	// ResponseEntity.ok(cozinha.get());
	//	}
	//	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
		
		return 	cozinhaService.salvar(cozinha);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
			@RequestBody Cozinha cozinha){
			Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
			
			if(cozinhaAtual.isPresent()) {
				BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id"); //outra forma de editar
				//cozinhaAtual.setNome(cozinha.getNome());
				Cozinha cozinhasalva = cozinhaService.salvar(cozinhaAtual.get());
				
				return ResponseEntity.ok(cozinhasalva);
			}
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public void remover (@PathVariable Long id){
			cozinhaService.excluir(id);
	}

	public Cozinha buscarOuFalhar(Long id) {	
		return cozinhaRepository.findById(id)
				.orElseThrow(()-> new CozinhaNaoEncontradaException(id));
					//	String.format("Cozinha Não Localizada !!!", id)));
		
	}
	
	
	
	
	

}




