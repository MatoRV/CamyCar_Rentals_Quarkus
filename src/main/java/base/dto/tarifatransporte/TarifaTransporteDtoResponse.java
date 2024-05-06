package base.dto.tarifatransporte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaTransporteDtoResponse {

    private String localidad;

    private Integer p4000;

    private Integer p9000;

    private Integer p14000;
}
