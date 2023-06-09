package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.model.Tablero;
import xyz.normadiza.normadiza.model.Tarea;
import xyz.normadiza.normadiza.payload.request.TareaReqRecord;
import xyz.normadiza.normadiza.payload.response.TareaResRecord;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.ITareaRepo;
import xyz.normadiza.normadiza.service.ITareaService;

import java.time.LocalDate;
import java.util.List;

@Service
public class TareaServiceImpl extends CRUDImpl<Tarea, Long> implements ITareaService {


    @Autowired
    private ITareaRepo repo;

    @Override
    protected IGenericRepo<Tarea, Long> getRepo() {
        return repo;
    }

    @Override
    @PreAuthorize("@autorizacionService.esSuTablero(#idTablero)")
    public Page<TareaResRecord> obtenerTareas(Long idTablero, Pageable pageable) {
        Page<TareaResRecord> tareas = repo.obtenerTarea(idTablero, pageable);

        return tareas;
    }

    @Override
    @PreAuthorize("@autorizacionService.esSuTablero(#tareaReqRecord.idTablero())")
    public TareaResRecord agregarTarea(TareaReqRecord tareaReqRecord) {
        Tarea tareaDB = repo.save( (Tarea) crearTarea(tareaReqRecord));

        TareaResRecord tarea = (TareaResRecord) crearTarea(tareaDB);
        return tarea;
    }

    @Override
    @PreAuthorize("@autorizacionService.esSuTablero(#tareaResRecord.idTablero())")
    public TareaResRecord actualizarTarea(TareaResRecord tareaResRecord) {
        repo.actualizarTarea(
                tareaResRecord.idTablero(),
                tareaResRecord.nombre(),
                tareaResRecord.descripcion(),
                tareaResRecord.inicioFecha(),
                tareaResRecord.finFecha(),
                LocalDate.now()
        );

        return tareaResRecord;
    }

    @Override
    @PreAuthorize("@autorizacionService.perteneceAlTablero(#idTarea)")
    public void eliminarTarea(Long idTarea) {
        repo.deleteById(idTarea);
    }

    /***
     * Metodo de utilidad para la creacion de dos objetos necesarios para la insercion del objeto en la base de datos:
     * el objeto de tipo Tarea (utilizando Spring Data); y el metodo para devolverlo al controlador: el objeto de tipo
     * TareaResRecord.
     *
     * @param tarea
     * @return
     */
    private static Object crearTarea(Object tarea){
        return switch (tarea) {
            case TareaReqRecord tareaReqRecord -> new Tarea(
                    tareaReqRecord.nombre(),
                    tareaReqRecord.descripcion(),
                    tareaReqRecord.finalizado(),
                    tareaReqRecord.inicioFecha(),
                    tareaReqRecord.finFecha(),
                    new Tablero(tareaReqRecord.idTablero())
                    );
            case Tarea t -> new TareaResRecord(
                    t.getIdTarea(),
                    t.getNombre(),
                    t.getDescripcion(),
                    t.getFinalizado(),
                    t.getInicioFecha(),
                    t.getFinalFecha(),
                    t.getTablero().getIdTablero()
            );
            case default -> null;
        };
    }
}
