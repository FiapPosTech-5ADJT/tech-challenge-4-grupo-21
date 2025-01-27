package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

import br.com.fiap.restauranteapi.domain.entity.Usuario;
import br.com.fiap.restauranteapi.infraestructure.persistence.jpa.entity.UsuarioJPAEntity;

public class UsuarioEntityConverter implements EntityConverter<Usuario, UsuarioJPAEntity>{
    @Override
    public UsuarioJPAEntity toEntity(Usuario usuarioDomainObj) {
        return new UsuarioJPAEntity(
                usuarioDomainObj.getId(),
                usuarioDomainObj.getCpf(),
                usuarioDomainObj.getNome(),
                usuarioDomainObj.getDdd(),
                usuarioDomainObj.getTelefone(),
                usuarioDomainObj.getEmail(),
                usuarioDomainObj.getDataCadastro()
        );
    }

    @Override
    public Usuario toDomainObj(UsuarioJPAEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getCpf(),
                usuarioEntity.getNome(),
                usuarioEntity.getDdd(),
                usuarioEntity.getTelefone(),
                usuarioEntity.getEmail(),
                usuarioEntity.getDataCadastro()
        );
    }
}
