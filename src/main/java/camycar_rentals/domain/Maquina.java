package camycar_rentals.domain;

import java.util.Objects;
import camycar_rentals.domain.enumerados.EstadoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "maquina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Maquina.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado", query = "SELECT m FROM Maquina m WHERE "
        + "(:tipoMaquina IS NULL OR EXISTS (FROM m.tipoMaquina t WHERE t.idTipoMaquina = :tipoMaquina)) AND "
        + "(:capacidadCarga IS NULL OR m.capacidadCarga = :capacidadCarga) AND "
        + "(:fabricante IS NULL OR m.fabricante = :fabricante) AND "
        + "(:estado IS NULL OR m.estado = :estado)")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maquina", nullable = false)
    private Integer idMaquina;

    @Column(name = "fabricante", nullable = false)
    private String fabricante;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "capacidad_carga", nullable = false)
    private Integer capacidadCarga;

    @Column(name = "estado", nullable = false)
    @Convert(converter = EstadoEnum.Converter.class)
    private EstadoEnum estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_maquina", referencedColumnName = "id_tipo_maquina", nullable = false)
    private TipoMaquina tipoMaquina;

    @Column(name = "peso", nullable = false)
    private Integer peso;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Maquina maquina = (Maquina) o;
        return Objects.equals(idMaquina, maquina.idMaquina) && Objects.equals(fabricante, maquina.fabricante)
                && Objects.equals(modelo, maquina.modelo)
                && Objects.equals(capacidadCarga, maquina.capacidadCarga)
                && estado == maquina.estado
                && Objects.equals(tipoMaquina, maquina.tipoMaquina);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idMaquina, fabricante, modelo, capacidadCarga, estado, tipoMaquina);
    }

    @Override
    public String toString() {
        return "Maquina{"
                + "idMaquina="
                + idMaquina
                + ", fabricante='"
                + fabricante
                + '\''
                + ", modelo='"
                + modelo
                + '\''
                + ", capacidadCarga="
                + capacidadCarga
                + ", estado="
                + estado
                + ", tipoMaquina="
                + tipoMaquina
                + '}';
    }
}
