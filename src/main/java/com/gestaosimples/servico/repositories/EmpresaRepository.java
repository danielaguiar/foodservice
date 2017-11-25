package com.gestaosimples.servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;

@Repository
public interface EmpresaRepository extends JpaRepository<PessoaJuridica, Long> {

}
