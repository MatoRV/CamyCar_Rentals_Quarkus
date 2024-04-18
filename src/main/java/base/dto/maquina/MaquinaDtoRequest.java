package base.dto.maquina;

import camycar_rentals.domain.enumerados.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaquinaDtoRequest {

    private String fabricante;

    private String modelo;

    private Integer capacidadCarga;

    private Integer idTipoMaquina;

    private EstadoEnum estado;
}
