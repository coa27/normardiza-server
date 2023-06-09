package xyz.normadiza.normadiza.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xyz.normadiza.normadiza.model.Tarea;
import xyz.normadiza.normadiza.payload.response.TareaResRecord;

import java.time.LocalDate;
import java.util.List;

public interface ITareaRepo extends IGenericRepo<Tarea, Long>{

    @Query("""
            SELECT new xyz.normadiza.normadiza.payload.response.TareaResRecord
            (t.idTarea, t.nombre, t.descripcion, t.finalizado, t.inicioFecha, t.finalFecha, t.tablero.idTablero)
            FROM Tarea t WHERE t.tablero.idTablero = :id
            """)
    Page<TareaResRecord> obtenerTarea(@Param("id") Long idTablero, Pageable pageable);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Tarea t
            SET t.nombre = :nombre, t.descripcion = :descripcion, t.inicioFecha = :fechaInicio, t.finalFecha = :fechaFin,
            t.updatedAt = :fechaActual
            WHERE t.idTarea = :id
            """)
    Integer actualizarTarea(@Param("id") Long idTablero,
                            @Param("nombre") String nombre,
                            @Param("descripcion") String descripcion,
                            @Param("fechaInicio") LocalDate fechaInicio,
                            @Param("fechaFin") LocalDate fechaFin,
                            @Param("fechaActual") LocalDate fechaActual);

}
