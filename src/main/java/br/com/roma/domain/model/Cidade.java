package br.com.roma.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade  extends RepresentationModel <Cidade>{
	


		@EqualsAndHashCode.Include
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
	//	@NotNull
		@NotBlank
		@Column(nullable = false)
		private String nome;
		
		@ManyToOne
		@JoinColumn(nullable = false)
		private Estado estado;

	}

