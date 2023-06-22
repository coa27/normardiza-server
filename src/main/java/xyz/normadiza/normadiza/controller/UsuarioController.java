package xyz.normadiza.normadiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.normadiza.normadiza.payload.response.UsuarioResRecord;
import xyz.normadiza.normadiza.service.IUsuarioService;

@RestController
@RequestMapping("/admin")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @PostMapping("/eliminar")
    public ResponseEntity<Void> eliminarUsuario(@RequestParam("id") Long idUsuario){
        service.eliminarUsuario(idUsuario);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/banear")
    public ResponseEntity<Void> banearUsuario(@RequestParam("id") Long idUsuario){
        service.banearUsuario(idUsuario);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Page<UsuarioResRecord>> obtenerUsuarios(Pageable pageable){
        return new ResponseEntity<>(service.obtenerUsuarios(pageable), HttpStatus.OK);
    }


}
