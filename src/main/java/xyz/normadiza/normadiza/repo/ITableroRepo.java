package xyz.normadiza.normadiza.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xyz.normadiza.normadiza.model.Tablero;
import xyz.normadiza.normadiza.payload.response.TablerosResRecord;

import java.time.LocalDate;
import java.util.List;

public interface ITableroRepo extends IGenericRepo<Tablero, Long>{

    @Query("""
        SELECT new xyz.normadiza.normadiza.payload.response.TablerosResRecord
        (t.idTablero, t.nombre, t.createdAt, usuario.idUsuario)
        FROM Tablero t WHERE t.usuario.idUsuario = :id
    """)
    Page<TablerosResRecord> obtenerTableros(@Param("id") Long idUsuario, Pageable pageable);

    @Query("SELECT t.idTablero FROM Tablero t WHERE usuario.idUsuario = :id")
    List<Long> obtenerIdTableros(@Param("id") Long idUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE Tablero t SET t.nombre = :nombre, t.updatedAt = :fecha WHERE t.idTablero = :id")
    Integer actualizarTablero(@Param("id")Long idTablero, @Param("nombre") String nombre, @Param("fecha")LocalDate fecha);
}
