package com.gestaosimples.corp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.corp.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    Email findByEdEmail(String edEmail);

}
