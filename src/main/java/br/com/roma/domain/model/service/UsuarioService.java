package br.com.roma.domain.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.exception.NegocioException;
import br.com.roma.domain.exception.UsuarioNaoEncontradoException;
import br.com.roma.domain.model.Grupo;
import br.com.roma.domain.model.Usuario;
import br.com.roma.domain.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	GrupoService grupoService;
	
	public List<Usuario> listar(){
		return	usuarioRepository.findAll();
		
	}
	
	public Usuario BuscarOufalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow
				(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	@Transactional
	public Usuario salvar (Usuario usuario) {
		
		
		Optional<Usuario> usuarioExistente = 
				usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario) ) {
			throw new NegocioException(
				String.format("Já existe um usuário cadastrado com o email %d", usuario.getEmail()));			
		}
				return usuarioRepository.save(usuario);	
	}
	
	@Transactional
	public void deletar(Long usuarioId) {
		Usuario usuario = BuscarOufalhar(usuarioId);
			usuarioRepository.delete(usuario);
		
	}
	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = BuscarOufalhar(usuarioId);
			Grupo grupo =	grupoService.buscarOufalhar(grupoId);
				usuario.associarPermissao(grupo);
		
	}
	@Transactional
	public void adesassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = BuscarOufalhar(usuarioId);
			Grupo grupo = grupoService.buscarOufalhar(grupoId);
				usuario.desassociarPermissao(grupo);
		
	}
	
	
	
}
