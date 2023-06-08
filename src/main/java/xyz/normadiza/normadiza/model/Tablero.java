package xyz.normadiza.normadiza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tablero")
public class Tablero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tablero")
    private Long idTablero;

    @NotBlank
    private String nombre;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tablero")
    private List<Tarea> tarea;

    public Tablero() {
    }

    public Tablero(Long idTablero) {
        this.idTablero = idTablero;
    }

    public Tablero(Long idTablero, String nombre, LocalDate createdAt, LocalDate updatedAt, Usuario usuario) {
        this.idTablero = idTablero;
        this.nombre = nombre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.usuario = usuario;
    }

    public Tablero(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public Long getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Long idTablero) {
        this.idTablero = idTablero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tarea> getTarea() {
        return tarea;
    }

    public void setTarea(List<Tarea> tarea) {
        this.tarea = tarea;
    }
}
