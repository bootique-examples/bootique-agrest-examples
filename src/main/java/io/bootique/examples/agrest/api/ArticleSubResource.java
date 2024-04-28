package io.bootique.examples.agrest.api;

import io.agrest.DataResponse;
import io.agrest.SimpleResponse;
import io.agrest.jaxrs3.AgJaxrs;
import io.bootique.examples.agrest.cayenne.Article;
import io.bootique.examples.agrest.cayenne.Domain;
import jakarta.ws.rs.DELETE;
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

/**
 * A sub-resource executed in a context of a given Domain ID.
 */
@Produces(MediaType.APPLICATION_JSON)
public class ArticleSubResource {

	private Configuration config;
	private int domainId;

	public ArticleSubResource(Configuration config, int domainId) {
		this.config = config;
		this.domainId = domainId;
	}

	@GET
	public DataResponse<Article> getAll(@Context UriInfo uriInfo) {
		return AgJaxrs.select(Article.class, config)
				.parent(Domain.class, domainId, String.valueOf(Domain.ARTICLES))
				.clientParams(uriInfo.getQueryParameters())
				.get();
	}

	@GET
	@Path("{articleId}")
	public DataResponse<Article> getOne(@PathParam("articleId") int id, @Context UriInfo uriInfo) {
		return AgJaxrs.select(Article.class, config)
				.parent(Domain.class, domainId, String.valueOf(Domain.ARTICLES))
				.byId(id)
				.clientParams(uriInfo.getQueryParameters()).get();
	}

	@POST
	public SimpleResponse create(String data) {
		return AgJaxrs.create(Article.class, config).parent(Domain.class, domainId, String.valueOf(Domain.ARTICLES)).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {
		return AgJaxrs.createOrUpdate(Article.class, config)
				.parent(Domain.class, domainId, String.valueOf(Domain.ARTICLES))
				.sync(data);
	}

	@DELETE
	@Path("{articleId}")
	public SimpleResponse delete(@PathParam("articleId") int id) {
		return AgJaxrs.delete(Article.class, config)
				.parent(Domain.class, domainId, String.valueOf(Domain.ARTICLES))
				.id(id)
				.sync();
	}

	@Path("{articleId}/tags")
	public TagSubResource tags(@PathParam("articleId") int articleId) {
		return new TagSubResource(config, articleId);
	}
}
