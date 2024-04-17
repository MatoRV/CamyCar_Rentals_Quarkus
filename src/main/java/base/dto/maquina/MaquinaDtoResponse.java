package base.dto.maquina;

import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaquinaDtoResponse {

    private Integer idMaquina;

    private String fabricante;

    private String modelo;

    private Integer capacidadCarga;

    private TipoMaquinaDtoResponse tipoMaquina;
}
