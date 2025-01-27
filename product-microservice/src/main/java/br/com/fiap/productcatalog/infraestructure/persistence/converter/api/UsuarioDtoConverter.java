package br.com.fiap.productcatalog.infraestructure.persistence.converter.api;

import br.com.fiap.restauranteapi.domain.dto.UsuarioRequestDto;
import br.com.fiap.restauranteapi.domain.dto.UsuarioResponseDto;
import br.com.fiap.restauranteapi.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter implements DtoConverter<UsuarioRequestDto, Usuario, UsuarioResponseDto> {

    @Override
    public UsuarioResponseDto toResponse(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getDataCadastro()
        );
    }

    @Override
    public Usuario toDomain(UsuarioRequestDto usuarioRequestDto) {
        return new Usuario(
                usuarioRequestDto.cpf(),
                usuarioRequestDto.nome(),
                usuarioRequestDto.ddd(),
                usuarioRequestDto.telefone(),
                usuarioRequestDto.email(),
                usuarioRequestDto.dataCadastro()
        );
    }

    @Override
    public void updateDomainFromDto(Usuario usuario, UsuarioRequestDto usuarioRequestDto) {
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setDdd(usuarioRequestDto.ddd());
        usuario.setTelefone(usuarioRequestDto.telefone());
        usuario.setEmail(usuarioRequestDto.email());
    }
}
