package camycar_rentals.domain;

import camycar_rentals.domain.enumerados.EstadoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
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

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "capacidad_carga")
    private Integer capacidadCarga;

    @Column(name = "estado")
    @Convert(converter = EstadoEnum.Converter.class)
    private EstadoEnum estado;

    @ManyToOne()
    @JoinColumn(name = "id_tipo_maquina", referencedColumnName = "id_tipo_maquina")
    private TipoMaquina tipoMaquina;
}
