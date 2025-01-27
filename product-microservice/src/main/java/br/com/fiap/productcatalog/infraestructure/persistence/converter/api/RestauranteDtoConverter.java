package br.com.fiap.productcatalog.infraestructure.persistence.converter.api;

import br.com.fiap.restauranteapi.domain.dto.RestauranteRequestDto;
import br.com.fiap.restauranteapi.domain.dto.RestauranteResponsetDto;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;

public class RestauranteDtoConverter implements DtoConverter<RestauranteRequestDto, Restaurante, RestauranteResponsetDto> {
    @Override
    public RestauranteResponsetDto toResponse(Restaurante domainObj) {
        return new RestauranteResponsetDto(domainObj.getNome(),
                domainObj.getLocalizacao(),
                domainObj.getHorarioFuncionamento(),
                domainObj.getTipoRestaurante(),
                domainObj.getCapacidade());
    }

    @Override
    public Restaurante toDomain(RestauranteRequestDto restauranteRequestDto) {
        return new Restaurante(restauranteRequestDto.nome(),
                restauranteRequestDto.localizacao(),
                restauranteRequestDto.horarioFuncionamento(),
                restauranteRequestDto.tipoRestaurante(),
                restauranteRequestDto.capacidade());
    }

    @Override
    public void updateDomainFromDto(Restaurante domainObj, RestauranteRequestDto restauranteRequestDto) {
        //TODO: Implement this method
    }
}
