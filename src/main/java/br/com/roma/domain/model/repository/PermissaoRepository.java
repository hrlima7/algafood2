package br.com.roma.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.roma.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao,Long> {

	
}
