package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;


import br.com.fiap.restauranteapi.domain.entity.Avaliacao;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.AvaliacaoJPAEntity;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.ReservaJPAEntity;

public class AvaliacaoEntityConverter implements EntityConverter<Avaliacao, AvaliacaoJPAEntity> {

    @Override
    public AvaliacaoJPAEntity toEntity(Avaliacao domainObj) {
        return new AvaliacaoJPAEntity(
                domainObj.getId(),
                new ReservaJPAEntity(domainObj.getIdReserva()),
                domainObj.getNota(),
                domainObj.getComentario()
        );
    }

    @Override
    public Avaliacao toDomainObj(AvaliacaoJPAEntity avaliacaoEntity) {
        return new Avaliacao(
                avaliacaoEntity.getId(),
                avaliacaoEntity.getReservaEntity().getId(),
                avaliacaoEntity.getNota(),
                avaliacaoEntity.getComentario()
        );
    }
}
