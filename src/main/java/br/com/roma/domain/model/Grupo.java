package br.com.roma.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	
	@ManyToMany
	@JoinTable(name="Grupo_permissao",
				joinColumns = @JoinColumn(name="grupo_id"),
				inverseJoinColumns= @JoinColumn(name="permissao_id"))
	private Set<Permissao> permissoes =  new HashSet<>();	

	
	public Boolean adicionarPermissao(Permissao permissao) {
		return getPermissoes().add(permissao);
	}
	
	public Boolean removerPermissao(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	
}
