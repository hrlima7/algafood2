package br.com.roma.domain.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.roma.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {

	 List<Cozinha> findByNomeContaining(String nome);

	
}
