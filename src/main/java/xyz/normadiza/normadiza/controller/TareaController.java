package xyz.normadiza.normadiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.normadiza.normadiza.payload.request.TareaReqRecord;
import xyz.normadiza.normadiza.payload.response.TareaResRecord;
import xyz.normadiza.normadiza.service.ITareaService;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private ITareaService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<TareaResRecord>> obtenerTableros(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.obtenerTableros(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TareaResRecord> agregarTarea(@RequestBody TareaReqRecord tareaReqRecord){
        return new ResponseEntity<>(service.agregarTarea(tareaReqRecord), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TareaResRecord> actualizarTarea(@RequestBody TareaResRecord tareaResRecord){
        return new ResponseEntity<>(service.actualizarTarea(tareaResRecord), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> actualizarTarea(@PathVariable("id") Long id){
        service.eliminarTarea(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
