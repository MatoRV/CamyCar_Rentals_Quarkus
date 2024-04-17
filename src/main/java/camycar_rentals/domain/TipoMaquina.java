package camycar_rentals.domain;

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
@Table(name = "Tipo_maquina")
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
}
