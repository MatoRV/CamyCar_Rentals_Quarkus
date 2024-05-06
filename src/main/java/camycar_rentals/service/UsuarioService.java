package camycar_rentals.service;

import java.util.List;
import base.constant.errores.ErroresGeneral;
import base.dto.usuario.UsuarioDtoRequest;
import base.dto.usuario.UsuarioDtoResponse;
import base.service.BaseService;
import camycar_rentals.domain.Usuario;
import camycar_rentals.dto.converters.ConverterDtoToJpa;
import camycar_rentals.dto.converters.ConverterJpaToDto;
import camycar_rentals.repository.UsuarioRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import io.quarkus.security.ForbiddenException;

@RequestScoped
public class UsuarioService extends BaseService<UsuarioRepository, Usuario, Integer> {

    @Inject
    ConverterJpaToDto converterJpaToDto;

    @Inject
    ConverterDtoToJpa converterDtoToJpa;

    @Transactional
    public UsuarioDtoResponse crear(UsuarioDtoRequest usuarioDtoRequest) {

        Usuario usuario = converterDtoToJpa.convertUsuario(usuarioDtoRequest);

        if (repository.obtenerPorDni(usuario)) {
            throw new ForbiddenException(ErroresGeneral.GEN_0002);
        }
        usuario = create(usuario);
        return converterJpaToDto.convertUsuario(usuario);
    }

    public List<UsuarioDtoResponse> obtenerClientes() {
        return converterJpaToDto.convertUsuarioList(findAll());
    }

    @Transactional
    public UsuarioDtoResponse editar(Integer idCliente, UsuarioDtoRequest usuarioDtoRequest) {

        Usuario usuarioData = find(idCliente);

        Usuario usuarioEdit = converterDtoToJpa.convertUsuario(usuarioData, usuarioDtoRequest);

        return converterJpaToDto.convertUsuario(edit(usuarioEdit));
    }

    @Transactional
    public UsuarioDtoResponse eliminar(Integer idCliente) {

        Usuario usuario = find(idCliente);

        return converterJpaToDto.convertUsuario(remove(usuario));
    }
}
