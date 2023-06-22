package xyz.normadiza.normadiza.service;

import xyz.normadiza.normadiza.payload.request.LoginReqRecord;
import xyz.normadiza.normadiza.payload.request.ValidarTokenReqRecord;
import xyz.normadiza.normadiza.payload.response.LoginResRecord;

public interface IAuthService {

    LoginResRecord acceder(LoginReqRecord loginReqRecordA);
    LoginResRecord registro(LoginReqRecord registroReqRecord);
    LoginResRecord validarToken(ValidarTokenReqRecord token);

}
