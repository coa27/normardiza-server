package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TareaResRecord> obtenerTableros(Long idTablero) {
        List<TareaResRecord> tareas = repo.obtenerTablero(idTablero);

        return tareas;
    }

    @Override
    public TareaResRecord agregarTarea(TareaReqRecord tareaReqRecord) {
        Tarea tareaDB = repo.save( (Tarea) crearTarea(tareaReqRecord));

        TareaResRecord tarea = (TareaResRecord) crearTarea(tareaDB);
        return tarea;
    }

    @Override
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
