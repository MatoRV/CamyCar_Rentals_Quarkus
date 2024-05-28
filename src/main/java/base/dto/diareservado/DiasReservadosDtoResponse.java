package base.dto.diareservado;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiasReservadosDtoResponse {

    private List<LocalDate> dias;
}
