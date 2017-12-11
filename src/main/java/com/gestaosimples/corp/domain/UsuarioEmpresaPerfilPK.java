package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.enuns.Perfil;

@Embeddable
public class UsuarioEmpresaPerfilPK implements Serializable {

    /**  */
    private static final long serialVersionUID = 4121743413766111334L;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_perfil")
    private Perfil perfil;

    public UsuarioEmpresaPerfilPK() {
    }

    public UsuarioEmpresaPerfilPK(Usuario usuario, Empresa empresa, Perfil perfil) {
        super();
        this.idUsuario = usuario.getId();
        this.idEmpresa = empresa.getId();
        this.perfil = perfil;

    }

    public UsuarioEmpresaPerfilPK(Long idUsuario, Long idEmpresa, Perfil perfil) {
        super();
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.perfil = perfil;
    }

    public UsuarioEmpresaPerfilPK(Long idEmpresa, Perfil perfil) {
        super();
        this.idEmpresa = idEmpresa;
        this.perfil = perfil;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
        result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioEmpresaPerfilPK other = (UsuarioEmpresaPerfilPK) obj;
        if (idEmpresa == null) {
            if (other.idEmpresa != null)
                return false;
        } else if (!idEmpresa.equals(other.idEmpresa))
            return false;
        if (idUsuario == null) {
            if (other.idUsuario != null)
                return false;
        } else if (!idUsuario.equals(other.idUsuario))
            return false;
        if (perfil != other.perfil)
            return false;
        return true;
    }

}
