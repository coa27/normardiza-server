package xyz.normadiza.normadiza.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.normadiza.normadiza.model.Tablero;
import xyz.normadiza.normadiza.payload.request.TableroReqRecord;
import xyz.normadiza.normadiza.payload.response.TablerosResRecord;

import java.util.List;

public interface ITableroService extends ICRUD<Tablero, Long>{

    TablerosResRecord agregarTablero(TableroReqRecord tablero);

    Page<TablerosResRecord> obtenerTableros(Pageable pageable);

    TablerosResRecord actualizarTablero(TablerosResRecord tablerosResRecord);

    void eliminarTablero(Long id);

}
