package xyz.normadiza.normadiza.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 18, message = "La contrasenia debe tener entre 3 a 18 caracteres")
    private String contrasenia;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();


    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tablero> tablero;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(){}

    public Usuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }

    public Usuario(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public List<Tablero> getTablero() {
        return tablero;
    }

    public void setTablero(List<Tablero> tablero) {
        this.tablero = tablero;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
