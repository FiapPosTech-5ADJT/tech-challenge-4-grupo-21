package br.com.fiap.productcatalog.infraestructure.persistence.converter.api;

public interface DtoConverter<REQUEST, DOMAIN, RESPONSE> {
    public RESPONSE toResponse(DOMAIN domainObj);

    public DOMAIN toDomain(REQUEST request);

    public void updateDomainFromDto(DOMAIN domainObj, REQUEST request);
}
