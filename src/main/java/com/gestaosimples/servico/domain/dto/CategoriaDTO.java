package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoriaDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    private Long id;

    @Length(max = 80, min = 5, message = "o tamanho deve ser entre 5 e 80 caracteres")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String nome) {
        super();
        this.nome = nome;
    }

    public CategoriaDTO(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
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

    //    public List<Produto> getProdutos() {
    //        return produtos;
    //    }
    //
    //    public void setProdutos(List<Produto> produtos) {
    //        this.produtos = produtos;
    //    }

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
        CategoriaDTO other = (CategoriaDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
