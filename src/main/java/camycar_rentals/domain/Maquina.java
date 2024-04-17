package camycar_rentals.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_maquina", referencedColumnName = "id_tipo_maquina")
    private TipoMaquina tipoMaquina;
}
