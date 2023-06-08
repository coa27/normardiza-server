package xyz.normadiza.normadiza.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.repo.ITableroRepo;
import xyz.normadiza.normadiza.repo.IUsuarioRepo;
import xyz.normadiza.normadiza.security.jwt.TokenService;
import xyz.normadiza.normadiza.security.model.DetallesDelUsuario;
import xyz.normadiza.normadiza.security.model.ModeloDeAutenticacion;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    @Autowired
    private TokenService service;

    @Autowired
    private IUsuarioRepo repo;

    @Autowired
    private ITableroRepo repoTablero;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            if (!request.getHeader("Authorization").startsWith("Bearer ")){
                throw new AuthorizationServiceException("no tiene el token");
            }

        }catch (NullPointerException e){
            throw new AuthorizationServiceException("no tiene el header");
        }

        String token = request.getHeader("Authorization").substring(7);

        DecodedJWT jwt = service.validarJwt(token);
        Usuario usuario = repo.findById(jwt.getClaim("idUsuario").asLong())
                .orElseThrow(() -> new UsernameNotFoundException("no existe usuario"));

        List<Long> tablerosIds = repoTablero.obtenerIdTableros(usuario.getIdUsuario());

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                usuario.getIdUsuario(),
//                usuario.getContrasenia(),
//                usuario.getRoles().stream()
//                        .map( rol -> new SimpleGrantedAuthority(rol.getNombre().toString())).collect(Collectors.toList())
//        );

        /***
         * Para que la autorizacion del usuario sobre los tableros sean mas faciles, se le agrega informacion como el
         * id del usuario y el id de todos los tableros que tiene.
         */
        ModeloDeAutenticacion authentication = new ModeloDeAutenticacion(
                usuario.getEmail(),
                usuario.getContrasenia(),
                usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getNombre().toString())).collect(Collectors.toList()),
                new DetallesDelUsuario(usuario.getIdUsuario(), tablerosIds.stream().collect(Collectors.toSet()))
        );

        securityContext.setAuthentication(authentication);

        SecurityContextHolder.setContext(securityContext);

        doFilter(request, response, filterChain);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/auth/acceder") ||
                request.getServletPath().equals("/auth/registrar") ||
                request.getServletPath().equals("/auth/validar");
    }
}
