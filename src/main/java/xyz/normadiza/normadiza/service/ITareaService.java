package xyz.normadiza.normadiza.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.normadiza.normadiza.model.Tarea;
import xyz.normadiza.normadiza.payload.request.TareaReqRecord;
import xyz.normadiza.normadiza.payload.response.TareaResRecord;

public interface ITareaService extends ICRUD<Tarea, Long>{

    Page<TareaResRecord> obtenerTareas(Long idTablero, Pageable pageable);

    TareaResRecord agregarTarea(TareaReqRecord tareaReqRecord);

    TareaResRecord actualizarTarea(TareaResRecord tareaResRecord);

    void eliminarTarea(Long idTarea);
}
