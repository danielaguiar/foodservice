package com.gestaosimples.corp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.corp.domain.UsuarioEmpresaPerfil;

@Repository
public interface UsuarioEmpresaPerfilRepository extends JpaRepository<UsuarioEmpresaPerfil, Long> {

}
