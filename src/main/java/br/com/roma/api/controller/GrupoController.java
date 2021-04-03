package br.com.roma.api.controller;

import java.util.List;
import java.util.Set;

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

import br.com.roma.domain.model.Grupo;
import br.com.roma.domain.model.Permissao;
import br.com.roma.domain.model.service.GrupoPermissaoService;
import br.com.roma.domain.model.service.GrupoService;
import br.com.roma.domain.model.service.PermissaoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	GrupoService grupoService;
	
	@Autowired
	PermissaoService permissaoService;
	
	@Autowired
	GrupoPermissaoService grupoPermissaoService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public	List<Grupo>listar (){
				return grupoService.listar();
		 
	}
	@GetMapping("/{grupoId}")
	public Grupo buscar(@PathVariable Long grupoId) {
		return grupoService.buscarOufalhar(grupoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Grupo salvar(@RequestBody Grupo grupo) {
		return grupoService.salvar(grupo);
	}
	
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.OK)
	public Grupo atualizar(@PathVariable Long grupoId,
				@RequestBody Grupo grupo) {
	Grupo grupoVelho = grupoService.buscarOufalhar(grupoId);
			
			BeanUtils.copyProperties(grupo, grupoVelho, "id");
		
			return grupoService.salvar(grupoVelho);
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long grupoId) {
			grupoService.deletar(grupoId);
	}
	
	
	@PutMapping("/{grupoId}/permissoes/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarPermissao(@PathVariable Long grupoId,
			@PathVariable Long permissaoId) {
		
		grupoPermissaoService.associarPermissao(grupoId, permissaoId);
		
	}

	@DeleteMapping("/{grupoId}/permissoes/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarPermissao(@PathVariable Long grupoId,
			@PathVariable Long permissaoId) {
		
		grupoPermissaoService.desassociarPermissao(grupoId, permissaoId);
		
	}
	
	@GetMapping("/{grupoId}/permissoes")
	@ResponseStatus(HttpStatus.OK)
	public Set<Permissao> buscarPermissao(@PathVariable Long grupoId){
		
		Grupo grupo = grupoService.buscarOufalhar(grupoId);
		return  grupo.getPermissoes();
		
	}
	
	

}
