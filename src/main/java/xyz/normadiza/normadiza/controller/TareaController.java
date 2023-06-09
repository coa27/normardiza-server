package xyz.normadiza.normadiza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /***
     *
     * @param idTablero para conocer de que tableros quieres las tareas, se debe enviar el id del tablero
     * @return
     */
    @GetMapping("/{idTablero}")
    public ResponseEntity<Page<TareaResRecord>> obtenerTareas(@PathVariable("idTablero") Long idTablero, Pageable pageable){
        return new ResponseEntity<>(service.obtenerTareas(idTablero, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TareaResRecord> agregarTarea(@RequestBody TareaReqRecord tareaReqRecord){
        return new ResponseEntity<>(service.agregarTarea(tareaReqRecord), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TareaResRecord> actualizarTarea(@RequestBody TareaResRecord tareaResRecord){
        return new ResponseEntity<>(service.actualizarTarea(tareaResRecord), HttpStatus.OK);
    }

    /***
     *
     * @param idTarea
     * @return
     */
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> actualizarTarea(@PathVariable("idTarea") Long idTarea){
        service.eliminarTarea(idTarea);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
