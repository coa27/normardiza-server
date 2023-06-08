package xyz.normadiza.normadiza.security.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ModeloDeAutenticacion implements Authentication {

    private boolean autenticado;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final DetallesDelUsuario detalles;

    public ModeloDeAutenticacion(String email, String password, Collection<? extends GrantedAuthority> authorities, DetallesDelUsuario detalles){
        this.authorities = authorities;
        this.detalles = detalles;
        this.password = password;
        this.email = email;
        setAuthenticated(true);
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
        return detalles;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public String getName() {
        return email;
    }

}
