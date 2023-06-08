package xyz.normadiza.normadiza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @Column(name = "id_tarea", columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    @NotBlank
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private Boolean finalizado;

    @Column(name = "inicio_fecha")
    private LocalDate inicioFecha;

    @Column(name = "final_fecha")
        private LocalDate finalFecha;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_tablero", nullable = false)
    private Tablero tablero;

    public Tarea() {
    }

    public Tarea(String nombre, String descripcion, Boolean finalizado, LocalDate inicioFecha, LocalDate finalFecha, Tablero tablero) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.finalizado = finalizado;
        this.inicioFecha = inicioFecha;
        this.finalFecha = finalFecha;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.tablero = tablero;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDate getInicioFecha() {
        return inicioFecha;
    }

    public void setInicioFecha(LocalDate inicioFecha) {
        this.inicioFecha = inicioFecha;
    }

    public LocalDate getFinalFecha() {
        return finalFecha;
    }

    public void setFinalFecha(LocalDate finalFecha) {
        this.finalFecha = finalFecha;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
