package com.gestaosimples.servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestaosimples.servico.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
