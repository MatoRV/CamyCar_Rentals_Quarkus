package base.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoResponse {

    private Integer idCliente;

    private String nombre;

    private String nombreUsuario;

    private String dniCliente;
}
