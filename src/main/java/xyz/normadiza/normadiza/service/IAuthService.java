package xyz.normadiza.normadiza.service;

import xyz.normadiza.normadiza.payload.request.LoginReqRecord;
import xyz.normadiza.normadiza.payload.request.ValidarTokenReqRecord;

public interface IAuthService {

    String acceder(LoginReqRecord loginReqRecordA);
    String registro(LoginReqRecord registroReqRecord);
    String validarToken(ValidarTokenReqRecord token);

}
