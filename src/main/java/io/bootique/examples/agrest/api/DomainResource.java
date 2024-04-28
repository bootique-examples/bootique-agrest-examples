package io.bootique.examples.agrest.api;

import io.agrest.DataResponse;
import io.agrest.SimpleResponse;
import io.agrest.jaxrs3.AgJaxrs;
import io.bootique.examples.agrest.cayenne.Domain;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("domain")
@Produces(MediaType.APPLICATION_JSON)
public class DomainResource {

	@Context
	private Configuration config;

	@GET
	public DataResponse<Domain> getAll(@Context UriInfo uriInfo) {
		return AgJaxrs.select(Domain.class, config).clientParams(uriInfo.getQueryParameters()).get();
	}

	@GET
	@Path("{domainId}")
	public DataResponse<Domain> getOne(@PathParam("domainId") int id, @Context UriInfo uriInfo) {
		return AgJaxrs.select(Domain.class, config).byId(id).clientParams(uriInfo.getQueryParameters()).getOne();
	}

	@POST
	public SimpleResponse create(String data) {

		// 'data' is a single object or an array of objects..

		return AgJaxrs.create(Domain.class, config).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {

		// 'data' is a single object or an array of objects... Objects without
		// IDs will be treated as "new". Agrest will try to locate objects
		// with IDs, and update them if found, or create if not

		return AgJaxrs.createOrUpdate(Domain.class, config).sync(data);
	}

	/**
	 * A relationship resource for a single domain, that routes all article
	 * requests to a sub-resource. Articles can only be viewed or manipulated in
	 * the context of a single web domain.
	 */
	@Path("{domainId}/articles")
	public ArticleSubResource articles(@PathParam("domainId") int domainId) {
		return new ArticleSubResource(config, domainId);
	}
}
