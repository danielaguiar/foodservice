package com.gestaosimples.arquitetura.security.util;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.corp.domain.Usuario;
import com.gestaosimples.servico.domain.enuns.Perfil;

public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long idEmpresa;
    private String login;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {
    }

    public UserSS(Usuario usuario, Long idEmpresaSelecionada) {
        super();
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.idEmpresa = idEmpresaSelecionada;

        //        if (!ObjetoUtil.isVazio(usuario.getPerfis())) {
        //            List<SimpleGrantedAuthority> perfisUsuario = new ArrayList<SimpleGrantedAuthority>();
        //            for (UsuarioEmpresaPerfil p : usuario.getPerfis()) {
        //                if (p.getId().getIdEmpresa().equals(idEmpresaSelecionada)) {
        //                    SimpleGrantedAuthority s = new SimpleGrantedAuthority(p.getId().getPerfil().getDescricao());
        //                    perfisUsuario.add(s);
        //                }
        //            }
        //            authorities = perfisUsuario;
        //        }

        if (!ObjetoUtil.isVazio(usuario.getPerfis())) {
            this.authorities =
                usuario.getPerfis().stream().filter(p -> p.getId().getIdEmpresa().equals(idEmpresa))
                    .map(x -> new SimpleGrantedAuthority(x.getId().getPerfil().getDescricao())).collect(Collectors.toList());

        }
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Perfil perfil) {
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresas(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
