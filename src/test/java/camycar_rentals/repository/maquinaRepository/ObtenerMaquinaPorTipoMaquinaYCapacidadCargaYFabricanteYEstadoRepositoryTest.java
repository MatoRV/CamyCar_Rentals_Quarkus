package camycar_rentals.repository.maquinaRepository;

import camycar_rentals.repository.MaquinaRepository;
import jakarta.inject.Inject;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ObtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstadoRepositoryTest {

    @Inject
    MaquinaRepository maquinaRepository;

    //    @Test
    //    @DisplayName("prueba para obtener una maquina")
    //    void obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstadoRepositoryOk() {
    //        // Given
    //        TipoMaquina tipoMaquina = new TipoMaquina(1,"Torito");
    //        Maquina maquina = new Maquina(1,"F1","M1",1500, EstadoEnum.DISPONIBLE,tipoMaquina);
    //
    //        // When
    //        List<Maquina> maquinas = maquinaRepository.obtenerMaquinaPorTipoMaquinaYCapacidadCargaYFabricanteYEstado(1,1500,"F1",EstadoEnum.DISPONIBLE);
    //
    //        // Then
    //        assertEquals(List.of(maquina), maquinas);
    //    }
}
