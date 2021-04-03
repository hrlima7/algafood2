package br.com.roma.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.roma.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}
