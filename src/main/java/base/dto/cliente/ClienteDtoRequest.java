package base.dto.cliente;

import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoRequest {

    @NotNull
    private String nombre;

    @NotNull
    private String nombreUsuario;

    @NotNull
    private String contrasena;

    @NotNull
    private String dniCliente;
}
