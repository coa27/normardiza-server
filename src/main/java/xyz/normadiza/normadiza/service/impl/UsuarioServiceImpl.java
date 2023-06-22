package xyz.normadiza.normadiza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.normadiza.normadiza.exceptions.customs.EntidadNoEncontradaException;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.model.projections.Projections;
import xyz.normadiza.normadiza.payload.response.UsuarioResRecord;
import xyz.normadiza.normadiza.repo.IGenericRepo;
import xyz.normadiza.normadiza.repo.IUsuarioRepo;
import xyz.normadiza.normadiza.service.IUsuarioService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Long> implements IUsuarioService {


    @Autowired
    private IUsuarioRepo repo;

    @Override
    protected IGenericRepo<Usuario, Long> getRepo() {
        return repo;
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        repo.deleteById(idUsuario);
    }

    @Override
    public void banearUsuario(Long idUsuario) {
        repo.banearUsuario(idUsuario);
    }

    /***
     *
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<UsuarioResRecord> obtenerUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = repo.obtenerUsuarios(pageable);

        usuarios.stream().forEach(System.out::println);

        Page<UsuarioResRecord> usuariosRes = usuarios.map(o -> new UsuarioResRecord(
                o.getIdUsuario(),
                o.getEmail(),
                o.getCreatedAt(),
                o.getUpdatedAt(),
                repo.obtenerRoles(o.getIdUsuario()).orElseThrow(() -> new EntidadNoEncontradaException("No encontrado"))
                        .stream().collect(Collectors.toSet()),
                o.getNoBaneado()
        ));

        return usuariosRes;
    }
}
