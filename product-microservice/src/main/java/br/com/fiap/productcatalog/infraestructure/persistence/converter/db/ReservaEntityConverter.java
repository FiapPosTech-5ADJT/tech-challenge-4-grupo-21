package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

import br.com.fiap.restauranteapi.domain.entity.Reserva;
import br.com.fiap.restauranteapi.domain.entity.ReservaDetalhada;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.ReservaJPAEntity;


public class ReservaEntityConverter implements EntityConverter<Reserva, ReservaJPAEntity> {
    /*private final UsuarioEntityConverter usuarioEntityConverter;

    public ReservaEntityConverter(UsuarioEntityConverter usuarioEntityConverter) {
        this.usuarioEntityConverter = usuarioEntityConverter;
    }*/

    @Override
    public ReservaJPAEntity toEntity(Reserva domainObj) {
      ReservaJPAEntity reservaEntity = new ReservaJPAEntity();
        reservaEntity.setId(domainObj.getId());
        reservaEntity.setQuantidadePessoas(domainObj.getQuantidadePessoas());
        reservaEntity.setDataHoraInicio(domainObj.getDataHoraInicio());
        reservaEntity.setDataHoraFim(domainObj.getDataHoraFim());
        reservaEntity.setStatus(domainObj.getStatus());

        return reservaEntity;
    }

    @Override
    public Reserva toDomainObj(ReservaJPAEntity reservaEntity) {
        return new Reserva(
                reservaEntity.getId(),
                reservaEntity.getRestauranteEntity().getId(),
                reservaEntity.getQuantidadePessoas(),
                reservaEntity.getDataHoraInicio(),
                reservaEntity.getDataHoraFim(),
                reservaEntity.getStatus()
        );
    }

    public ReservaDetalhada toDetailedDomainObj(ReservaJPAEntity reservaEntity) {
        return new ReservaDetalhada(
                reservaEntity.getId(),
                //usuarioEntityConverter.toDomainObj(reservaEntity.getUsuarioEntity()),
                reservaEntity.getQuantidadePessoas(),
                reservaEntity.getDataHoraInicio(),
                reservaEntity.getDataHoraFim(),
                reservaEntity.getStatus()
        );
    }
}
