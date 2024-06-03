package camycar_rentals.dto.converters;

import java.time.LocalDate;
import java.util.List;
import base.dto.localidad.LocalidadDtoResponse;
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
    @Mapping(source = "reserva.maquina.idMaquina", target = "maquina.idMaquina")
    @Mapping(source = "reserva.maquina.fabricante", target = "maquina.fabricante")
    @Mapping(source = "reserva.maquina.modelo", target = "maquina.modelo")
    @Mapping(source = "reserva.maquina.capacidadCarga", target = "maquina.capacidadCarga")
    @Mapping(source = "reserva.maquina.estado", target = "maquina.estado")
    @Mapping(source = "reserva.maquina.tipoMaquina.nombre", target = "maquina.tipoMaquina.nombre")
    @Mapping(source = "reserva.maquina.peso", target = "maquina.peso")
    ReservaDtoResponse convertReservaDtoResponse(Reserva reserva, List<LocalDate> dias);

    List<ReservaDtoResponse> convertReservaDtoResponseList(List<Reserva> reservas);

    @Mapping(source = "localidad.nombre", target = "localidad")
    TarifaTransporteDtoResponse convertTarifaTransporteDtoResponse(TarifaTransporte tarifaTransporte);

    List<TarifaTransporteDtoResponse> convertTarifaTransporteDtoResponseList(List<TarifaTransporte> tarifaTransporteList);

    LocalidadDtoResponse converLocalidadDtoResponse(String localidad);

    @Mapping(source = "localidades", target = "localidades.localidad")
    List<LocalidadDtoResponse> convertLocalidadDtoResponseList(List<String> localidades);
}
