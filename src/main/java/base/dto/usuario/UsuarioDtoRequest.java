package base.dto.usuario;

import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoRequest {

    @NotNull
    private String nombre;

    private String apellido1;

    private String apellido2;

    private String correo;

    @NotNull
    private String contrasena;

    @NotNull
    private String dniUsuario;
}
