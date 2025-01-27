package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

import br.com.fiap.restauranteapi.domain.entity.HorarioFuncionamento;
import br.com.fiap.restauranteapi.domain.entity.Localizacao;
import br.com.fiap.restauranteapi.domain.entity.Restaurante;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.RestauranteJPAEntity;

public class RestauranteEntityConverter implements EntityConverter<Restaurante, RestauranteJPAEntity>{

    public RestauranteJPAEntity toEntity(Restaurante restaurante) {
        return RestauranteJPAEntity.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .cep(restaurante.getLocalizacao().getCep())
                .logradouro(restaurante.getLocalizacao().getLogradouro())
                .numero(restaurante.getLocalizacao().getNumero())
                .complemento(restaurante.getLocalizacao().getComplemento())
                .bairro(restaurante.getLocalizacao().getBairro())
                .cidade(restaurante.getLocalizacao().getCidade())
                .estado(restaurante.getLocalizacao().getEstado())
                .pais(restaurante.getLocalizacao().getPais())
                .diasSemanaList(restaurante.getHorarioFuncionamento().getDiasSemanaList())
                .horarioAbertura(restaurante.getHorarioFuncionamento().getHorarioAbertura())
                .horarioFechamento(restaurante.getHorarioFuncionamento().getHorarioFechamento())
                .tipoRestaurante(restaurante.getTipoRestaurante())
                .capacidade(restaurante.getCapacidade())
                .build();
    }

    public Restaurante toDomainObj(RestauranteJPAEntity restauranteJpa) {
        Localizacao localizacao = new Localizacao(
                restauranteJpa.getCep(),
                restauranteJpa.getLogradouro(),
                restauranteJpa.getNumero(),
                restauranteJpa.getComplemento(),
                restauranteJpa.getBairro(),
                restauranteJpa.getCidade(),
                restauranteJpa.getEstado(),
                restauranteJpa.getPais()
        );

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento(
                restauranteJpa.getDiasSemanaList(),
                restauranteJpa.getHorarioAbertura(),
                restauranteJpa.getHorarioFechamento()
        );

        return new Restaurante(
                restauranteJpa.getId(),
                restauranteJpa.getNome(),
                localizacao,
                horarioFuncionamento,
                restauranteJpa.getTipoRestaurante(),
                restauranteJpa.getCapacidade(),
                restauranteJpa.getCreatedAt(),
                restauranteJpa.getUpdatedAt()
        );
    }
}
