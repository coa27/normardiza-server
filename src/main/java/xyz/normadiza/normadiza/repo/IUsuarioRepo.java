package xyz.normadiza.normadiza.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.normadiza.normadiza.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios_roles (id_usuario, id_rol) VALUES (?, 2)", nativeQuery = true)
    Integer guardarRol(Long idUsuario);

//    Optional<List<TableroId>> findAllByIdUsuario(Long idUsuario);
}
