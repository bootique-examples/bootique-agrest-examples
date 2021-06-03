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
import io.bootique.agrest.demo.cayenne.Tag;

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
		return Ag.select(Tag.class, config).parent(Article.class, articleId, String.valueOf(Article.TAGS)).uri(uriInfo)
				.get();
	}

	@GET
	@Path("{tagId}")
	public DataResponse<Tag> getOne(@PathParam("tagId") int id, @Context UriInfo uriInfo) {
		return Ag.select(Tag.class, config).parent(Article.class, articleId, String.valueOf(Article.TAGS)).byId(id)
				.uri(uriInfo).get();
	}

	@POST
	public SimpleResponse create(String data) {
		return Ag.create(Tag.class, config).parent(Article.class, articleId, String.valueOf(Article.TAGS)).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {
		return Ag.createOrUpdate(Tag.class, config).parent(Article.class, articleId, String.valueOf(Article.TAGS))
				.sync(data);
	}

	@DELETE
	@Path("{tagId}")
	public SimpleResponse delete(@PathParam("articleId") int id) {
		return Ag.delete(Tag.class, config).parent(Article.class, articleId, String.valueOf(Article.TAGS)).id(id).delete();
	}

}
