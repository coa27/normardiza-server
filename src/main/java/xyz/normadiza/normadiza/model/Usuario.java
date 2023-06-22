package xyz.normadiza.normadiza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import xyz.normadiza.normadiza.model.projections.Projections;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/***
 * La relacion usuarios <-> roles no esta mapeada por JPA, por lo que se debe realizar un Native Query para obtenerlo
 * y realizar la presentacion correcta de la clase Usuario mostrando lo necesario, principalmente para el acceso de admin.
 */
@Entity
@Table(name = "usuario")
@SqlResultSetMapping(
        name = "usuariosRoles",
        classes = {
                @ConstructorResult(
                        targetClass = Projections.UsuariosRoles.class,
                        columns = {
                                @ColumnResult(name = "id_usuario", type = Long.class),
                                @ColumnResult(name = "id_rol", type = Long.class)
                        }
                )
        }
)
@NamedNativeQuery(
        name = "Usuario.obtenerRoles",
        query = "SELECT * FROM usuarios_roles WHERE id_usuario = :id",
        resultSetMapping = "usuariosRoles"
)
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

    @Column(name = "no_baneado")
    private Boolean noBaneado;

    public Usuario(){}

    public Usuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }

    public Usuario(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
        this.noBaneado = true;
    }

    public Usuario(Long idUsuario, String email, LocalDate createdAt, LocalDate updatedAt, Boolean noBaneado) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.noBaneado = noBaneado;
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

    public Boolean getNoBaneado() {
        return noBaneado;
    }

    public void setNoBaneado(Boolean noBaneado) {
        this.noBaneado = noBaneado;
    }
}
