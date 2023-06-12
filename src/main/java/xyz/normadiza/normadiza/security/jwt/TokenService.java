package xyz.normadiza.normadiza.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.exceptions.customs.TokenNoValido;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${security.jwt.secret-key}")
    private String secret;

    Logger logger = LoggerFactory.getLogger(TokenService.class);


    public String generarToken(Long idUsuario){
        try {
            String token = JWT.create()
                    .withIssuer("coasth_")
                    .withClaim("idUsuario", idUsuario)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(60*60*2))
                    .sign(Algorithm.HMAC512(secret));

            return token;
        }catch (JWTCreationException e){
            throw new JWTCreationException(e.getMessage(), e.getCause());
        }
    }

    /***
     *
     * Metodo para validar un token. Se crea un verifier, para verificar si el token es valido o no, con su metodo verify,
     * el propio metodo verify tambien verifica si el token esta expirado o no (ya que a la instancia del jwtVerifier
     * no le hemos puesto que acepte tokens expirados).
     *
     * @param token
     * @return
     */
    public DecodedJWT validarJwt(String token){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(secret))
                    .withIssuer("coasth_")
                    .withClaimPresence("idUsuario")
                    .build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT;

        }catch (JWTVerificationException e){
            throw new TokenNoValido(e.getMessage());
        }
    }

    public static boolean tokenExpirado(DecodedJWT decodedJWT){
        Instant instant = decodedJWT.getExpiresAtAsInstant();
        return instant.isAfter(Instant.now());
    }

}
