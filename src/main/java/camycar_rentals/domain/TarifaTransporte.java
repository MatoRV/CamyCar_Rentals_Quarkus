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
@Table(name = "tarifa_transporte")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa_transporte",nullable = false)
    private Integer idTarifaTransporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLocalidad")
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad", nullable = false)
    private Localidad localidad;

    @Column(name = "p4000",nullable = false)
    private Integer p4000;

    @Column(name = "p9000",nullable = false)
    private Integer p9000;

    @Column(name = "p14000", nullable = false)
    private Integer p14000;
}
