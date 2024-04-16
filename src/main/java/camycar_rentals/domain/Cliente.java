package camycar_rentals.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "usuario_cliente", nullable = false, unique = true)
    private String usuarioCliente;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "dni_cliente", nullable = false, unique = true)
    private String dniCliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente) && Objects.equals(nombre, cliente.nombre) && Objects.equals(usuarioCliente, cliente.usuarioCliente) && Objects.equals(contrasena, cliente.contrasena) && Objects.equals(dniCliente, cliente.dniCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, usuarioCliente, contrasena, dniCliente);
    }
}
