package com.gestaosimples.servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.servico.domain.corp.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    Email findByEdEmail(String edEmail);

}
