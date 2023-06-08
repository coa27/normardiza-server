package xyz.normadiza.normadiza.payload.request;

import java.time.LocalDate;

public record TareaReqRecord (String nombre,
                              String descripcion,
                              Boolean finalizado,
                              LocalDate inicioFecha,
                              LocalDate finFecha,
                              Long idTablero) {
}
