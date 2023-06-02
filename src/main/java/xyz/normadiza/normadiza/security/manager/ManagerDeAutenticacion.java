package xyz.normadiza.normadiza.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import xyz.normadiza.normadiza.security.provider.AutenticacionProvider;

@Component
public class ManagerDeAutenticacion implements AuthenticationManager {

    @Autowired
    private AutenticacionProvider autenticacionProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (autenticacionProvider.supports(authentication.getClass())){
            return autenticacionProvider.authenticate(authentication);
        }

        throw new BadCredentialsException("del manager");
    }
}
