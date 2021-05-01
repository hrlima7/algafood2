package br.com.roma.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import br.com.roma.api.model.input.UsuarioModelDTO;
import br.com.roma.domain.model.Grupo;
import br.com.roma.domain.model.Usuario;
import br.com.roma.domain.model.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioModelDTO> listar(){
		
		List<Usuario> usuarios = usuarioService.listar();
				return toCollectorModel(usuarios);
		
		
	}
	
	@GetMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioModelDTO buscar(@PathVariable Long usuarioId) {
	
		Usuario usuario = usuarioService.BuscarOufalhar(usuarioId);
	
		
		return toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar (@Valid @RequestBody Usuario usuario) {
			return	usuarioService.salvar(usuario);
		
	}
	
	@PutMapping("/{usuarioId}")
	public Usuario atualizar(@RequestBody Usuario usuario,
					@PathVariable Long usuarioId) {
			Usuario usuarioAtual = usuarioService.BuscarOufalhar(usuarioId);
			BeanUtils.copyProperties(usuario, usuarioAtual, "id","grupos");
	
			return usuarioService.salvar(usuarioAtual);
	}
	

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long usuarioId) {
		usuarioService.deletar(usuarioId);
	}
	
	@PutMapping("/{usuarioId}/grupos/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarGrupo(@PathVariable Long usuarioId,
				@PathVariable Long grupoId) {
		usuarioService.associarGrupo(usuarioId, grupoId);
		
	}
	
	@DeleteMapping("/{usuarioId}/grupos/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void dasassociarGrupo(@PathVariable Long usuarioId,
				@PathVariable Long grupoId) {
		usuarioService.adesassociarGrupo(usuarioId, grupoId);
		
	}
	
	@GetMapping("/{usuarioId}/grupos")
	@ResponseStatus(HttpStatus.OK)
	public Set<Grupo> buscarGrupos(@PathVariable Long  usuarioId){
			Usuario usuario = usuarioService.BuscarOufalhar(usuarioId);
			return usuario.getGrupos();
	}
	
	public UsuarioModelDTO toModel(Usuario usuario) {
		UsuarioModelDTO usuarioModelDTO  = new UsuarioModelDTO();
	
		usuarioModelDTO.setNome(usuario.getNome());
		usuarioModelDTO.setEmail(usuario.getEmail());
		
		usuarioModelDTO.add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
				.buscar(usuario.getId())).withSelfRel());
		
		usuarioModelDTO.add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
				.listar()).withRel("usuarios"));
				
		
		return usuarioModelDTO;
		
		
	}
	
	public List<UsuarioModelDTO> toCollectorModel(List<Usuario> usuarios){
		
		return usuarios.stream().
				map( usuario -> toModel(usuario))
						.collect(Collectors.toList());
	} 
	
	
}
