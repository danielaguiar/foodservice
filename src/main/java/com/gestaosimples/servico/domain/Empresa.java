package com.gestaosimples.servico.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.gestaosimples.corp.domain.Aplicacao;
import com.gestaosimples.corp.domain.Atividade;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.corp.domain.Endereco;
import com.gestaosimples.corp.domain.PessoaJuridica;
import com.gestaosimples.corp.domain.Telefone;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.domain.enuns.Status;

@Entity(name = "t_empresa")
public class Empresa implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_empresa")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaJuridica empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "cs_cliente", length = 1, nullable = true)
    private Status status;

    @OneToOne
    @JoinColumn(name = "id_email", referencedColumnName = "id_email", nullable = true)
    private Email email;

    @OneToOne
    @JoinColumn(name = "id_telefone", referencedColumnName = "id_telefone", nullable = true)
    private Telefone telefone;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.REMOVE})
    @JoinTable(name = "T_EMPRESA_APLICACAO", joinColumns = {@JoinColumn(name = "ID_EMPRESA")}, inverseJoinColumns = {@JoinColumn(name = "ID_APLICACAO")})
    private Set<Aplicacao> aplicacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.REMOVE})
    @JoinTable(name = "T_EMPRESA_ATIVIDADE", joinColumns = {@JoinColumn(name = "ID_EMPRESA")}, inverseJoinColumns = {@JoinColumn(name = "ID_ATIVIDADE")})
    private Set<Atividade> ramoAtividades;

    public Empresa() {
    }

    public Empresa(Long idEmpresa) {
        super();
        this.id = idEmpresa;
    }

    public Empresa(EmpresaDTO dto) {
        this.id = dto.getIdEmpresa();
        this.empresa = new PessoaJuridica(dto);
        this.status = Status.A;
        this.telefone = new Telefone(dto.getTelefone());
        this.endereco = new Endereco(dto.getEndereco());
        this.email = dto.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Aplicacao> getAplicacoes() {
        return aplicacoes;
    }

    public void setAplicacoes(Set<Aplicacao> aplicacoes) {
        this.aplicacoes = aplicacoes;
    }

    public Set<Atividade> getRamoAtividades() {
        return ramoAtividades;
    }

    public void setRamoAtividades(Set<Atividade> ramoAtividades) {
        this.ramoAtividades = ramoAtividades;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Empresa other = (Empresa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
