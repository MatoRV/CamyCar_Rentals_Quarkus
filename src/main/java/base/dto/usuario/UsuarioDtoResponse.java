package base.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoResponse {

    private Integer idUsuario;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String correo;

    private String dniUsuario;
}
