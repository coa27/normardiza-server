package xyz.normadiza.normadiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.normadiza.normadiza.payload.request.TableroReqRecord;
import xyz.normadiza.normadiza.payload.response.TablerosResRecord;
import xyz.normadiza.normadiza.service.ITableroService;

import java.util.List;

@RestController
@RequestMapping("/tablero")
public class TableroController {

    @Autowired
    private ITableroService service;

    @GetMapping
    public ResponseEntity<Page<TablerosResRecord>> obtenerTableros(Pageable pageable){
        Page<TablerosResRecord> tableros = service.obtenerTableros(pageable);

        return new ResponseEntity<>(tableros, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TablerosResRecord> agregarTablero(@RequestBody TableroReqRecord tableroReqRecord){
        TablerosResRecord tablero = service.agregarTablero(tableroReqRecord);

        return new ResponseEntity<TablerosResRecord>(tablero, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TablerosResRecord> actualizarTablero(@RequestBody TablerosResRecord tablerosResRecord){
        TablerosResRecord tablero = service.actualizarTablero(tablerosResRecord);

        return new ResponseEntity<>(tablero, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTablero(@RequestParam Long idTablero){
        service.eliminarTablero(idTablero);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
