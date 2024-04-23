package camycar_rentals.repository.maquinaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.enumerados.EstadoEnum;
import camycar_rentals.repository.MaquinaRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstadoRepositoryTest {

    @Inject
    MaquinaRepository maquinaRepository;

    @Test
    @DisplayName("prueba para obtener una maquina")
    void obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstadoRepositoryOk() {
        // Given
        TipoMaquina tipoMaquina = new TipoMaquina(1,"Torito");
        Maquina maquina = new Maquina(1,"F1","M1",1500, EstadoEnum.DISPONIBLE,tipoMaquina);

        // When
        List<Maquina> maquinas = maquinaRepository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(1,1500,"F1",EstadoEnum.DISPONIBLE);

        // Then
        assertEquals(List.of(maquina), maquinas);
    }
}
