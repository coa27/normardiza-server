package xyz.normadiza.normadiza.payload.response;

import java.time.LocalDate;

public record TareaResRecord(Long id,
                             String nombre,
                             String descripcion,
                             Boolean finalizado,
                             LocalDate inicioFecha,
                             LocalDate finFecha,
                             Long idTablero) {
}
