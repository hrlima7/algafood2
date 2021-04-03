package br.com.roma.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.roma.infraestructure.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name= "cozinha")
public class Cozinha {
	
	@NotNull(groups = Groups.CadastroRestaurante.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private	Long id;
    

	//@NotBlank
    private String nome;
	
	
//	private OffsetDateTime dt_cadastro;
    
  // @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List <Restaurante> restaurantes =  new ArrayList<>() ;

}
