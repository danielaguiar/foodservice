package com.gestaosimples.servico.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.gestaosimples.corp.services.UsuarioService;

public abstract class AbstractService {

    // services
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected EmpresaService empresaService;

    @Autowired
    protected ClienteService clienteService;

}
