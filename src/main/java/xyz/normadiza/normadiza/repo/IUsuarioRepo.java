package xyz.normadiza.normadiza.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xyz.normadiza.normadiza.model.Usuario;
import xyz.normadiza.normadiza.model.projections.Projections;

import java.util.Collection;
import java.util.Optional;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios_roles (id_usuario, id_rol) VALUES (?, 2)", nativeQuery = true)
    Integer guardarRol(Long idUsuario);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE usuario
            SET no_baneado = not no_baneado
            WHERE id_usuario = :idUsuario
            """, nativeQuery = true)
    Integer banearUsuario(@Param("idUsuario") Long idUsuario);

    @Query(nativeQuery = true)
    Optional<Collection<Projections.UsuariosRoles>> obtenerRoles(@Param("id")Long id);

    @Query("""
            SELECT new xyz.normadiza.normadiza.model.Usuario
            (idUsuario, email, createdAt, updatedAt, noBaneado) 
            FROM Usuario
            """)
    Page<Usuario> obtenerUsuarios(Pageable pageable);

}
