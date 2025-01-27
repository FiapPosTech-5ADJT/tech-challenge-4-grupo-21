package br.com.fiap.productcatalog.infraestructure.persistence.converter.api;


import br.com.fiap.restauranteapi.domain.dto.AvaliacaoRequestDto;
import br.com.fiap.restauranteapi.domain.dto.AvaliacaoResponseDto;
import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoDtoConverter implements DtoConverter<AvaliacaoRequestDto, Avaliacao, AvaliacaoResponseDto> {

    @Override
    public AvaliacaoResponseDto toResponse(Avaliacao domainObj) {
        return new AvaliacaoResponseDto(domainObj.getId());
    }

    @Override
    public Avaliacao toDomain(AvaliacaoRequestDto avaliacaoRequestDto) {
        return new Avaliacao(
                avaliacaoRequestDto.reservaId(),
                avaliacaoRequestDto.nota(),
                avaliacaoRequestDto.comentario()
        );
    }

    @Override
    public void updateDomainFromDto(Avaliacao domainObj, AvaliacaoRequestDto avaliacaoRequestDto) {
        domainObj.setNota(avaliacaoRequestDto.nota());
        domainObj.setComentario(avaliacaoRequestDto.comentario());
    }
}
