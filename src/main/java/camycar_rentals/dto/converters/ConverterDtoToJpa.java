package camycar_rentals.dto.converters;

import java.util.List;
import base.dto.maquina.MaquinaDtoRequest;
import base.dto.reserva.ReservaDtoRequest;
import base.dto.tipomaquina.TipoMaquinaDtoRequest;
import base.dto.usuario.UsuarioDtoRequest;
import camycar_rentals.domain.Maquina;
import camycar_rentals.domain.Reserva;
import camycar_rentals.domain.TipoMaquina;
import camycar_rentals.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ConverterDtoToJpa {

    Usuario convertUsuario(UsuarioDtoRequest usuarioDtoRequest);

    Usuario convertUsuario(@MappingTarget Usuario usuarioData, UsuarioDtoRequest request);

    TipoMaquina convertTipoMaquina(TipoMaquinaDtoRequest tipoMaquinaDtoRequest);

    TipoMaquina convertTipoMaquina(@MappingTarget TipoMaquina tipoMaquina, TipoMaquinaDtoRequest tipoMaquinaDtoRequest);

    List<TipoMaquina> converTipoMaquinaList(List<TipoMaquinaDtoRequest> tipoMaquinaDtoRequestList);

    Maquina convertMaquina(MaquinaDtoRequest maquinaDtoRequest);

    Maquina convertMaquina(@MappingTarget Maquina maquina, MaquinaDtoRequest maquinaDtoRequest);

    List<Maquina> convertMaquinaList(List<MaquinaDtoRequest> maquinaDtoRequestList);

    Reserva convertReserva(ReservaDtoRequest reservaDtoRequest);

    Reserva convertReserva(@MappingTarget Reserva reserva, ReservaDtoRequest reservaDtoRequest);

    List<Reserva> convertReservaList(List<ReservaDtoRequest> reservaDtoRequests);
}
