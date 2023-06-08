package xyz.normadiza.normadiza.security.model;

import java.util.Set;

public record DetallesDelUsuario(Long idUsuario, Set<Long> idsTableros) {
}
