package xyz.normadiza.normadiza.service;

import xyz.normadiza.normadiza.model.Tarea;
import xyz.normadiza.normadiza.payload.request.TareaReqRecord;
import xyz.normadiza.normadiza.payload.response.TareaResRecord;

import java.util.List;

public interface ITareaService extends ICRUD<Tarea, Long>{

    List<TareaResRecord> obtenerTableros(Long idTablero);

    TareaResRecord agregarTarea(TareaReqRecord tareaReqRecord);

    TareaResRecord actualizarTarea(TareaResRecord tareaResRecord);

    void eliminarTarea(Long idTarea);
}
