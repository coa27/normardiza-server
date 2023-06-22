package xyz.normadiza.normadiza.payload.response;

import xyz.normadiza.normadiza.model.projections.Projections;

import java.time.LocalDate;
import java.util.Set;

public record UsuarioResRecord (Long idUsuario, String email, LocalDate createdAt, LocalDate updatedAt, Set<Projections.UsuariosRoles> roles, Boolean noBaneado) {
}
