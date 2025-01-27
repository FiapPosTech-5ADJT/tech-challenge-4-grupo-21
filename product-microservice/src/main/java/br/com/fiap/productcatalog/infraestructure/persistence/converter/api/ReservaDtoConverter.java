package br.com.fiap.productcatalog.infraestructure.persistence.converter.api;


import br.com.fiap.restauranteapi.domain.dto.ReservaDetalhadaResponseDto;
import br.com.fiap.restauranteapi.domain.dto.ReservaRequestDto;
import br.com.fiap.restauranteapi.domain.dto.ReservaResponsetDto;
import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.ReservaDetalhada;
import org.springframework.stereotype.Component;

@Component
public class ReservaDtoConverter implements DtoConverter<ReservaRequestDto, Reserva, ReservaResponsetDto> {
    @Override
    public ReservaResponsetDto toResponse(Reserva domainObj) {
        return new ReservaResponsetDto(domainObj.getId(), domainObj.getStatus());
    }

    public ReservaDetalhadaResponseDto toDetailedResponse(ReservaDetalhada domainObj) {
        return new ReservaDetalhadaResponseDto(
                domainObj.getId(),
                domainObj.getQuantidadePessoas(),
                domainObj.getDataHoraInicio(),
                domainObj.getDataHoraFim(),
                domainObj.getStatus()
        );
    }

    @Override
    public Reserva toDomain(ReservaRequestDto reservaRequestDto) {
        return new Reserva(
                reservaRequestDto.idUsuario(),
                reservaRequestDto.idRestaurante(),
                reservaRequestDto.quantidadePessoas(),
                reservaRequestDto.dataHoraInicio(),
                reservaRequestDto.dataHoraFim()
        );
    }

    @Override
    public void updateDomainFromDto(Reserva domainObj, ReservaRequestDto reservaRequestDto) {
        domainObj.setIdUsuario(reservaRequestDto.idUsuario());
        domainObj.setIdRestaurante(reservaRequestDto.idRestaurante());
        domainObj.setQuantidadePessoas(reservaRequestDto.quantidadePessoas());
        domainObj.setDataHoraInicio(reservaRequestDto.dataHoraInicio());
        domainObj.setDataHoraFim(reservaRequestDto.dataHoraFim());
    }
}
