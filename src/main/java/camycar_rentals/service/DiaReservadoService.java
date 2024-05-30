package camycar_rentals.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import base.service.BaseService;
import camycar_rentals.domain.DiaReservado;
import camycar_rentals.repository.DiaReservadoRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;

@RequestScoped
public class DiaReservadoService extends BaseService<DiaReservadoRepository, DiaReservado, Integer> {

    @Transactional
    public List<DiaReservado> eliminarDiasReservadosPasadosDeFecha(List<DiaReservado> diaReservados) {
        List<DiaReservado> dr = new ArrayList<>();
        diaReservados.forEach(diaReservado -> {
            if (diaReservado.getDia().isBefore(LocalDate.now())) {
                repository.remove(diaReservado);
            } else {
                dr.add(diaReservado);
            }
        });
        return dr;
    }

    public List<LocalDate> obtenerDiasPorIdMaquina(Integer idMaquina) {
        List<DiaReservado> diasReservados = repository.obtenerDiasReservadosPorIdMaquina(idMaquina);
        diasReservados = eliminarDiasReservadosPasadosDeFecha(diasReservados);
        return diasReservados.stream().map(DiaReservado::getDia).toList();
    }

    @Transactional
    public List<LocalDate> eliminarDiasReservados(List<DiaReservado> diaReservados) {
        List<LocalDate> dias = diaReservados.stream().map(DiaReservado::getDia).toList();
        diaReservados.forEach(dr -> repository.remove(dr));
        return dias;
    }

}
