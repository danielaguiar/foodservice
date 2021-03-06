package com.gestaosimples.corp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.corp.domain.Aplicacao;

@Repository
public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {

}
