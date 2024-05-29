package camycar_rentals.dto.converters;

import java.time.LocalDate;
import java.util.List;
import base.dto.maquina.MaquinaDtoResponse;
import base.dto.reserva.ReservaDtoResponse;
import base.dto.tarifatransporte.TarifaTransporteDtoResponse;
import base.dto.tipomaquina.TipoMaquinaDtoResponse;
import base.dto.usuario.UsuarioDtoResponse;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TarifaTransporte;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ConverterJpaToDto {

    UsuarioDtoResponse convertUsuario(Usuario usuario);

    List<UsuarioDtoResponse> convertUsuarioList(List<Usuario> usuarios);

    TipoMaquinaDtoResponse convertTipoMaquinaDtoResponse(TipoMaquina tipoMaquina);

    List<TipoMaquinaDtoResponse> convertTipoMaquinaDtoResponseList(List<TipoMaquina> tipoMaquinas);

    @Mapping(source = "maquina.tipoMaquina.nombre", target = "tipoMaquina.nombre")
    @Mapping(source = "dias", target = "diasReservados.dias")
    MaquinaDtoResponse convertMaquinaDtoResponse(Maquina maquina, List<LocalDate> dias);

    List<MaquinaDtoResponse> convertMaquinaDtoResponseList(List<Maquina> maquinas);

    @Mapping(source = "reserva.usuario.nombre", target = "nombreUsuario")
    @Mapping(source = "dias", target = "maquina.diasReservados.dias")
    ReservaDtoResponse convertReservaDtoResponse(Reserva reserva, List<LocalDate> dias);

    List<ReservaDtoResponse> convertReservaDtoResponseList(List<Reserva> reservas);

    @Mapping(source = "localidad.nombre", target = "localidad")
    TarifaTransporteDtoResponse convertTarifaTransporteDtoResponse(TarifaTransporte tarifaTransporte);

    List<TarifaTransporteDtoResponse> convertTarifaTransporteDtoResponseList(List<TarifaTransporte> tarifaTransporteList);
}
