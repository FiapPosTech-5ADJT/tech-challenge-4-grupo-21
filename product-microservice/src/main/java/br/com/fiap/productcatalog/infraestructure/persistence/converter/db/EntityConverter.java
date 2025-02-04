package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

@SuppressWarnings("squid:S119")
public interface EntityConverter<DOMAIN, ENTITY> {

    public ENTITY toEntity(DOMAIN domainObj);

    public DOMAIN toDomainObj(ENTITY entity);
}
