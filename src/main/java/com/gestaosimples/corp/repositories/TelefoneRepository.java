package com.gestaosimples.corp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.corp.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
