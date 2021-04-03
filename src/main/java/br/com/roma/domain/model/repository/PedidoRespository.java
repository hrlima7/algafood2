package br.com.roma.domain.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.roma.domain.filter.VendaDiariaFilter;
import br.com.roma.domain.model.Pedido;
import br.com.roma.domain.model.dto.VendaDiaria;
@Repository
public interface PedidoRespository extends JpaRepository<Pedido,Long> {
	
	@Query("from Pedido where codigo =:codigo ")
	Optional <Pedido>findBy(String codigo);

 
}
