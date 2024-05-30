package camycar_rentals.domain;

import java.time.LocalDate;
import jakarta.persistence.Column;
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
@Table(name = "dia_reservado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "DiaReservado.obtenerDiasReservadosPorIdMaquina", query = "SELECT dr FROM DiaReservado dr WHERE dr.maquina.idMaquina = :maquina")
public class DiaReservado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_reservado", nullable = false)
    private Integer idDiaReservado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maquina", referencedColumnName = "id_maquina")
    private Maquina maquina;

    @Column(name = "dia", nullable = false)
    private LocalDate dia;
}
