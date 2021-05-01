package br.com.roma.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.api.ResourceUriHelper;
import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.exception.NegocioException;
import br.com.roma.domain.model.Cidade;
import br.com.roma.domain.model.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	CidadeService cidadeService;

	@SuppressWarnings("deprecation")
	@ApiOperation("Lista as cidades")
	@GetMapping
	public CollectionModel<Cidade> listar() {
		
		List<Cidade> cidade = cidadeService.buscarTodos();
		
	CollectionModel<Cidade> cidadesModel =  new CollectionModel<>(cidade);
	
	cidadesModel.forEach(cidade1 -> {
		cidade1.add(WebMvcLinkBuilder.linkTo(methodOn(CidadeController.class)
				.buscar(cidade1.getId())).withSelfRel());
		
		cidade1.add(WebMvcLinkBuilder.linkTo(methodOn(CidadeController.class)
				.listar()).withRel("cidades"));
		
		cidade1.getEstado()
				.add(WebMvcLinkBuilder.linkTo(methodOn(EstadoController.class)
						.buscar(cidade1.getEstado().getId())).withSelfRel());
		
	});
	CollectionModel<Cidade> cidadesCollectionModel = new CollectionModel<>(cidadesModel);
	
			cidadesCollectionModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
			
	return cidadesCollectionModel;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
		Cidade cidade = cidadeService.buscarOuFalhar(id);

		cidade.add(WebMvcLinkBuilder.linkTo(methodOn(CidadeController.class)
				.buscar(cidade.getId())).withSelfRel());
		cidade.add(WebMvcLinkBuilder.linkTo(methodOn(CidadeController.class)
				.listar()).withRel("cidades"));
		cidade.getEstado()
				.add(WebMvcLinkBuilder.linkTo(methodOn(EstadoController.class)
						.buscar(cidade.getEstado().getId())).withSelfRel());

		//cidade.getEstado()
		//.add(WebMvcLinkBuilder.linkTo(EstadoController.class)
		//		.slash(cidade.getEstado().getId()).withSelfRel());

		
		// cidade.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
		// .withRel("cidades"));

		// cidade.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
		// .slash(cidade.getId()).withSelfRel());

		// cidade.add(new Link("http://localhost:8080/cidades/1")) ;
		// cidade.add(new
		// Link("http://localhost:8080/cidades",IanaLinkRelations.COLLECTION));
		// cidade.add(new Link("http://localhost:8080/cidades","cidades"));

		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody @Valid Cidade cidade) {
		try {
			Cidade cidadeSalva = cidadeService.salvar(cidade);

			ResourceUriHelper.addUriResponseHeader(cidadeSalva.getId());

			return cidadeSalva;
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	// @ResponseStatus(HttpStatus.CREATED)
	public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidadeNova) {

		Cidade cidadeVelha = cidadeService.buscarOuFalhar(id);
		BeanUtils.copyProperties(cidadeNova, cidadeVelha, "id");

		try {
			return cidadeService.salvar(cidadeNova);

		} catch (EntidadeNaoEncontradaException e) {
			System.out.println("deu errado");
			throw new NegocioException(e.getMessage()) {
			};
			// return ResponseEntity.badRequest().body(e.getMessage());
		}
		// if(cidadeVelha != null ) {
		// cidadeVelha.setNome(cidade.getNome());
		// cidadeVelhal.setEstado(cidade.getEstado()); //}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Cidade> excluir(@PathVariable Long id) {

		cidadeService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * @ExceptionHandler(EntidadeNaoEncontradaException.class) public
	 * ResponseEntity<?> tratarEstadoNaoEncontradoException(
	 * EntidadeNaoEncontradaException e){ Problema problema = Problema.builder()
	 * .dataHora(LocalDateTime.now()) .mensagem(e.getMessage()).build();
	 * 
	 * 
	 * return ResponseEntity.status(HttpStatus.NOT_FOUND).body (problema); }
	 * 
	 * @ExceptionHandler(NegocioException.class) public ResponseEntity<?>
	 * tratarNegocioException(NegocioException e){ return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST). body(e.getMessage()); }
	 */
}
