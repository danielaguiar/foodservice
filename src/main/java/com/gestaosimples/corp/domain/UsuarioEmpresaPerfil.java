package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.enuns.Perfil;

@Entity
@Table(name = "t_usuario_empresa_perfil")
public class UsuarioEmpresaPerfil implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsuarioEmpresaPerfilPK id;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    private Empresa empresa;

    public UsuarioEmpresaPerfil() {
    }

    public UsuarioEmpresaPerfil(Empresa empresa, Perfil perfil) {
        this.id = new UsuarioEmpresaPerfilPK(empresa.getId(), perfil);
        this.empresa = new Empresa(empresa.getId());
    }

    public UsuarioEmpresaPerfil(Usuario usuario, Empresa empresa, Perfil perfil) {
        this.id = new UsuarioEmpresaPerfilPK(usuario, empresa, perfil);
    }

    public UsuarioEmpresaPerfil(UsuarioEmpresaPerfilPK id) {
        super();
        this.id = id;
    }

    public UsuarioEmpresaPerfilPK getId() {
        return id;
    }

    public void setId(UsuarioEmpresaPerfilPK id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
