package xyz.normadiza.normadiza.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.payload.response.UsuarioResRecord;

public interface IUsuarioService extends ICRUD<Usuario, Long>{

    void eliminarUsuario(Long idUsuario);

    void banearUsuario(Long idUsuario);

    Page<UsuarioResRecord> obtenerUsuarios(Pageable pageable);

}
