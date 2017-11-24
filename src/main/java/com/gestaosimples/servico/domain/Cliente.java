package com.gestaosimples.servico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaosimples.servico.domain.corp.Endereco;
import com.gestaosimples.servico.domain.enuns.PerfilEnum;
import com.gestaosimples.servico.domain.enuns.TipoClienteEnum;

@Entity(name = "t_cliente")
public class Cliente implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_cliente")
    private Long id;

    @Column(name = "ds_nome", length = 60)
    private String nome;

    @Column(name = "ed_email", length = 120)
    private String email;

    @Column(name = "nr_cpf_cnpj", length = 20)
    private String cpfOuCnpj;

    @Enumerated(EnumType.STRING)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "cl_tipo_cliente", length = 1)
    private TipoClienteEnum tipo;

    @JsonIgnore
    @Column(name = "ds_senha")
    private String senha;

    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Transient
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @ElementCollection
    @CollectionTable(name = "t_telefone")
    private Set<String> telefones = new HashSet<String>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_perfis")
    private Set<PerfilEnum> perfis = new HashSet<PerfilEnum>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Cliente() {
        addPerfil(PerfilEnum.C);
    }

    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoClienteEnum tipo, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.senha = senha;
        addPerfil(PerfilEnum.C);
    }

    public Cliente(String nome, String email, String cpfOuCnpj, TipoClienteEnum tipo) {
        super();
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        addPerfil(PerfilEnum.C);
    }

    public Cliente(String nome, String email, String cpfOuCnpj, TipoClienteEnum tipo, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.senha = senha;
        addPerfil(PerfilEnum.C);
    }

    public Cliente(String nome, String email) {
        super();
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoClienteEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoClienteEnum tipo) {
        this.tipo = tipo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<PerfilEnum> getPerfis() {
        //        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
        return perfis;
    }

    public void addPerfil(PerfilEnum perfil) {
        //        this.perfis.add(perfil.getCodigo());
        this.perfis.add(perfil);
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
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
