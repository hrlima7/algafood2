package br.com.roma.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Email
	private String email;
	@JsonIgnore
	private String senha;
	
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	
	
	@ManyToMany
	@JoinTable(name = "usuarios_Gupos",
				joinColumns = @JoinColumn(name = "id_usuario"),
				inverseJoinColumns = @JoinColumn(name ="id_grupo"))
	private Set<Grupo> grupos = new HashSet<>();
	
	
	
	public Boolean associarPermissao(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	public Boolean desassociarPermissao(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	

}
