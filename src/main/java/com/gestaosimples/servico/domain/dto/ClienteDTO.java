package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.resources.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    private Long id;

    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public ClienteDTO(Long id, String nome, String email) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public ClienteDTO(String nome, String email) {
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
        ClienteDTO other = (ClienteDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
