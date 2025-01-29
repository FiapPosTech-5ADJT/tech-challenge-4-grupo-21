package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

public interface EntityConverter<DOMAIN, ENTITY> {

    public ENTITY toEntity(DOMAIN domainObj);

    public DOMAIN toDomainObj(ENTITY entity);
}
