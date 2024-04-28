package io.bootique.examples.agrest.api;


import io.agrest.DataResponse;
import io.agrest.SimpleResponse;
import io.agrest.jaxrs3.AgJaxrs;
import io.bootique.examples.agrest.cayenne.Article;
import io.bootique.examples.agrest.cayenne.Tag;
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

@Produces(MediaType.APPLICATION_JSON)
public class TagSubResource {

	private Configuration config;
	private int articleId;

	public TagSubResource(Configuration config, int articleId) {
		this.config = config;
		this.articleId = articleId;
	}

	@GET
	public DataResponse<Tag> getAll(@Context UriInfo uriInfo) {
		return AgJaxrs.select(Tag.class, config)
				.parent(Article.class, articleId, String.valueOf(Article.TAGS))
				.clientParams(uriInfo.getQueryParameters())
				.get();
	}

	@GET
	@Path("{tagId}")
	public DataResponse<Tag> getOne(@PathParam("tagId") int id, @Context UriInfo uriInfo) {
		return AgJaxrs.select(Tag.class, config)
				.parent(Article.class, articleId, String.valueOf(Article.TAGS))
				.byId(id)
				.clientParams(uriInfo.getQueryParameters())
				.get();
	}

	@POST
	public SimpleResponse create(String data) {
		return AgJaxrs.create(Tag.class, config).parent(Article.class, articleId, String.valueOf(Article.TAGS)).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {
		return AgJaxrs.createOrUpdate(Tag.class, config)
				.parent(Article.class, articleId, String.valueOf(Article.TAGS))
				.sync(data);
	}

	@DELETE
	@Path("{tagId}")
	public SimpleResponse delete(@PathParam("articleId") int id) {
		return AgJaxrs
				.delete(Tag.class, config)
				.parent(Article.class, articleId, String.valueOf(Article.TAGS))
				.id(id)
				.sync();
	}

}
