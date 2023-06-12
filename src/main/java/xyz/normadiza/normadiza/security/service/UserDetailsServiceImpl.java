package xyz.normadiza.normadiza.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.security.model.UsuarioUserDetails;
import xyz.normadiza.normadiza.repo.IUsuarioRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repo.findByEmail(username).orElseThrow(() -> new BadCredentialsException("Credenciales erroneas"));

        return new UsuarioUserDetails(usuario);
    }
}
