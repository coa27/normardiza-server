package xyz.normadiza.normadiza.service.impl;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.hibernate.sql.ast.tree.from.TableJoin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.payload.request.LoginReqRecord;
import xyz.normadiza.normadiza.payload.request.ValidarTokenReqRecord;
import xyz.normadiza.normadiza.repo.IRolRepo;
import xyz.normadiza.normadiza.repo.IUsuarioRepo;
import xyz.normadiza.normadiza.security.jwt.TokenService;
import xyz.normadiza.normadiza.security.manager.ManagerDeAutenticacion;
import xyz.normadiza.normadiza.security.model.ModeloDeAutenticacion;
import xyz.normadiza.normadiza.service.IAuthService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthServiceImpl implements IAuthService {

    Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private ManagerDeAutenticacion managerDeAutenticacion;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepo repo;

    /***
     *
     * Se valida, por medio del ManagerDeAutenticacion, si el usuario existe y tiene las credenciales correspondientes.
     * De ser exitoso el proceso de autenticacion, se procede con la creacion del JWT, donde la unica informacion que se
     * enviara al servicio del JWT, es el Id del usuario. Tambien se pudo solo enviar el correo del usuario, pero para
     * mas practicas con la implementacion de la clase Authentication, de Spring Security, se busco agregarle a esta
     * el id del usuario y que sea por medio de este, que se conozca la informacion requerida.
     *
     * @param loginReqRecord
     * @return String devuelve el JWT correspondiente a la sesion del usuario.
     */
    @Override
    public String acceder(LoginReqRecord loginReqRecord) {
        ModeloDeAutenticacion authentication = (ModeloDeAutenticacion) managerDeAutenticacion.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqRecord.email(), loginReqRecord.password())
        );

        String token = tokenService.generarToken((Long) authentication.getDetails());

        return token;
    }

    @Override
    public String registro(LoginReqRecord registroReqRecord) {
        if(repo.findByEmail(registroReqRecord.email()).isPresent()){
            return null;
        }

        Usuario usuario = new Usuario(registroReqRecord.email(), registroReqRecord.password());
        repo.save(usuario);
        repo.guardarRol(usuario.getIdUsuario());

        String token = tokenService.generarToken(usuario.getIdUsuario());

        return token;
    }

    @Override
    public String validarToken(ValidarTokenReqRecord token) {
        DecodedJWT decodedJWT = tokenService.validarJwt(token.token());
        String tokenNuevo;

        //Si al token le faltan 30 minutos para expirar, se crea un nuevo token, si no,se devuelve el mismo token validado
        if ((Instant.now().until(decodedJWT.getExpiresAtAsInstant(), ChronoUnit.MINUTES)) < 30){
            Long id =  Long.parseLong(decodedJWT.getClaim("idUsuario").toString());
            tokenNuevo = tokenService.generarToken(id);
            return tokenNuevo;
        }

        tokenNuevo = token.token();
        return tokenNuevo;
    }


}
