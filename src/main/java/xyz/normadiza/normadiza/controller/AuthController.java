package xyz.normadiza.normadiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.normadiza.normadiza.payload.request.LoginReqRecord;
import xyz.normadiza.normadiza.payload.request.ValidarTokenReqRecord;
import xyz.normadiza.normadiza.payload.response.LoginResRecord;
import xyz.normadiza.normadiza.service.IAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService service;

    @PostMapping("/acceder")
    public ResponseEntity<LoginResRecord> iniarSesion(@RequestBody @Validated LoginReqRecord loginReqRecord){
        LoginResRecord token = new LoginResRecord(service.acceder(loginReqRecord));

        return new ResponseEntity<LoginResRecord>(token, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/registrar")
    public ResponseEntity<LoginResRecord> registrarUsuario(@RequestBody @Validated LoginReqRecord registroReqRecord){
        LoginResRecord token = new LoginResRecord(service.registro(registroReqRecord));

        return new ResponseEntity<LoginResRecord>(token, HttpStatus.CREATED);
    }

    @PostMapping("/validar")
    public ResponseEntity<LoginResRecord> validarToken(@RequestBody @Validated ValidarTokenReqRecord token){
        LoginResRecord tokenNuevo = new LoginResRecord(service.validarToken(token));

        return new ResponseEntity<LoginResRecord>(tokenNuevo, HttpStatusCode.valueOf(200));
    }

}
