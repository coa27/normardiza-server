package xyz.normadiza.normadiza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tablero")
public class TableroController {

    @GetMapping
    public String obtenerTablero(){
        return "tablero";
    }

}
