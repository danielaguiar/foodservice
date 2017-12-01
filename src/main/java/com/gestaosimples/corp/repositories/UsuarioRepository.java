package com.gestaosimples.corp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.corp.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String email);

    Usuario findByEmailEdEmail(String login);

    Usuario findByEmailId(Long id);

}
