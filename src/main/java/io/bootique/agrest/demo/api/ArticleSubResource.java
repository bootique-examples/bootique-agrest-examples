package io.bootique.agrest.demo.api;

import javax.ws.rs.DELETE;
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

import io.agrest.Ag;
import io.agrest.DataResponse;
import io.agrest.SimpleResponse;
import io.bootique.agrest.demo.cayenne.Article;
import io.bootique.agrest.demo.cayenne.Domain;

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
		return Ag.select(Article.class, config).toManyParent(Domain.class, domainId, Domain.ARTICLES).uri(uriInfo)
				.get();
	}

	@GET
	@Path("{articleId}")
	public DataResponse<Article> getOne(@PathParam("articleId") int id, @Context UriInfo uriInfo) {
		return Ag.select(Article.class, config).toManyParent(Domain.class, domainId, Domain.ARTICLES).byId(id)
				.uri(uriInfo).get();
	}

	@POST
	public SimpleResponse create(String data) {
		return Ag.create(Article.class, config).toManyParent(Domain.class, domainId, Domain.ARTICLES).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {
		return Ag.createOrUpdate(Article.class, config).toManyParent(Domain.class, domainId, Domain.ARTICLES)
				.sync(data);
	}

	@DELETE
	@Path("{articleId}")
	public SimpleResponse delete(@PathParam("articleId") int id) {
		return Ag.delete(Article.class, config).toManyParent(Domain.class, domainId, Domain.ARTICLES).id(id)
				.delete();
	}

	@Path("{articleId}/tags")
	public TagSubResource tags(@PathParam("articleId") int articleId) {
		return new TagSubResource(config, articleId);
	}
}
