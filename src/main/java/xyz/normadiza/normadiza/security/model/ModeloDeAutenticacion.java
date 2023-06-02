package xyz.normadiza.normadiza.security.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ModeloDeAutenticacion implements Authentication {

    private boolean autenticado;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Long id;

    public ModeloDeAutenticacion(String email, String password, Collection<? extends GrantedAuthority> authorities, Long id){
        this.authorities = authorities;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean isAuthenticated() {
        return autenticado;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.autenticado = isAuthenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return id;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public String toString() {
        return "ModeloDeAutenticacion{" +
                "autenticado=" + autenticado +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", id=" + id +
                '}';
    }
}
