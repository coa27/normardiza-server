package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.model.Tablero;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.payload.request.TableroReqRecord;
import xyz.normadiza.normadiza.payload.response.TablerosResRecord;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.ITableroRepo;
import xyz.normadiza.normadiza.security.model.DetallesDelUsuario;
import xyz.normadiza.normadiza.service.ITableroService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableroServiceImpl extends CRUDImpl<Tablero, Long> implements ITableroService {


    @Autowired
    private ITableroRepo repo;

    @Override
    protected IGenericRepo<Tablero, Long> getRepo() {
        return repo;
    }

    @Override
    public Page<TablerosResRecord> obtenerTableros(Pageable pageable) {
        Page<TablerosResRecord> tableros = repo.obtenerTableros(obtenerIdUsuario(), pageable);

        return tableros;
    }

    @Override
    public TablerosResRecord agregarTablero(TableroReqRecord tablero) {
        Tablero tableroDB = repo.save(new Tablero(tablero.nombre(), new Usuario(obtenerIdUsuario())));

        TablerosResRecord tableros = new TablerosResRecord(tableroDB.getIdTablero(), tableroDB.getNombre(), tableroDB.getCreatedAt(), tableroDB.getUsuario().getIdUsuario());
        return tableros;
    }

    @Override
    public TablerosResRecord actualizarTablero(TablerosResRecord tablerosResRecord) {
        DetallesDelUsuario detallesDelUsuario = (DetallesDelUsuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!detallesDelUsuario.idsTableros().contains(tablerosResRecord.idTablero())){
            throw new AccessDeniedException("Este no es su tablero compa");
        }

        repo.actualizarTablero(tablerosResRecord.idTablero(), tablerosResRecord.nombre(), LocalDate.now());

        return tablerosResRecord;
    }

    @Override
    public void eliminarTablero(Long id) {
        DetallesDelUsuario detallesDelUsuario = (DetallesDelUsuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!detallesDelUsuario.idsTableros().contains(id)){
            throw new AccessDeniedException("Este no es su tablero compa");
        }

        repo.deleteById(id);
    }

    private static Long obtenerIdUsuario(){
        DetallesDelUsuario detallesDelUsuario = (DetallesDelUsuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return detallesDelUsuario.idUsuario();
    }
}
