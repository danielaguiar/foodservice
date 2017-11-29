package com.gestaosimples.servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.servico.domain.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Empresa findByNrCnpj(String nrCnpj);

    Empresa findByEmailEdEmail(String email);

}
