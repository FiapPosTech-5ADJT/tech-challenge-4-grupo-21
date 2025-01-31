package br.com.fiap.productcatalog.infraestructure.persistence.converter.api;

@SuppressWarnings("squid:S119")
public interface DtoConverter<Req, Domain, Res> {
    public Res toResponse(Domain domainObj);

    public Domain toDomain(Req request);

    public void updateDomainFromDto(Domain domainObj, Req request);
}
