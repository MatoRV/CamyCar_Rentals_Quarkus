package base.dto.maquina;

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
}
