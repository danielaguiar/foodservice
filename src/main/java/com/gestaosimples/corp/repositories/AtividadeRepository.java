package com.gestaosimples.corp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.corp.domain.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
