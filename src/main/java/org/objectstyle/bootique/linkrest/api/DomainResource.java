package org.objectstyle.bootique.linkrest.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.objectstyle.bootique.linkrest.cayenne.Domain;

import com.nhl.link.rest.DataResponse;
import com.nhl.link.rest.LinkRest;
import com.nhl.link.rest.SimpleResponse;

@Path("domain")
@Produces(MediaType.APPLICATION_JSON)
public class DomainResource {

	@Context
	private Configuration config;

	@GET
	public DataResponse<Domain> getAll(@Context UriInfo uriInfo) {
		return LinkRest.select(Domain.class, config).uri(uriInfo).select();
	}

	@GET
	@Path("{domainId}")
	public DataResponse<Domain> getOne(@PathParam("domainId") int id, @Context UriInfo uriInfo) {
		return LinkRest.select(Domain.class, config).byId(id).uri(uriInfo).selectOne();
	}

	@POST
	public SimpleResponse create(String data) {

		// 'data' is a single object or an array of objects..

		return LinkRest.create(Domain.class, config).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {

		// 'data' is a single object or an array of objects... Objects without
		// IDs will be treated as "new". LinkRest will try to locate objects
		// with IDs, and update them if found, or create if not

		return LinkRest.createOrUpdate(Domain.class, config).sync(data);
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
