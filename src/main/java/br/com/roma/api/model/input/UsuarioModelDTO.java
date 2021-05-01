package br.com.roma.api.model.input;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Usuario")
public class UsuarioModelDTO extends RepresentationModel <UsuarioModelDTO> {
	
	private String nome;
	private String email;

}
