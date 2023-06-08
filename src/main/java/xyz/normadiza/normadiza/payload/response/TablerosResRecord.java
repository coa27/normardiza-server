package xyz.normadiza.normadiza.payload.response;

import java.time.LocalDate;

public record TablerosResRecord(Long idTablero, String nombre, LocalDate fechaCreacion, Long idUsuario) {
}
