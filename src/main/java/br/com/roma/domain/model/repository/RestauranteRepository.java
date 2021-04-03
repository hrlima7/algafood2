package br.com.roma.domain.model.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.roma.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long>{
	
public List<Restaurante> find(String nome,
			BigDecimal taxaFreteInicial,BigDecimal taxaFreteFinal);


	
}
