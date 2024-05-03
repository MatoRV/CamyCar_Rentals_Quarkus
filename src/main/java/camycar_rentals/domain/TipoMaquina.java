package camycar_rentals.domain;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_maquina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoMaquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_maquina", nullable = false)
    private Integer idTipoMaquina;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TipoMaquina that = (TipoMaquina) o;
        return Objects.equals(idTipoMaquina, that.idTipoMaquina) && Objects.equals(nombre, that.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idTipoMaquina, nombre);
    }
    @Override
    public String toString() {
        return "TipoMaquina{" + "idTipoMaquina=" + idTipoMaquina + ", nombre='" + nombre + '\'' + '}';
    }
}
