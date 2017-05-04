package io.bootique.linkrest.demo.api;

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

import io.bootique.linkrest.demo.cayenne.Article;
import io.bootique.linkrest.demo.cayenne.Tag;

import com.nhl.link.rest.DataResponse;
import com.nhl.link.rest.LinkRest;
import com.nhl.link.rest.SimpleResponse;

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
		return LinkRest.select(Tag.class, config).toManyParent(Article.class, articleId, Article.TAGS).uri(uriInfo)
				.get();
	}

	@GET
	@Path("{tagId}")
	public DataResponse<Tag> getOne(@PathParam("tagId") int id, @Context UriInfo uriInfo) {
		return LinkRest.select(Tag.class, config).toManyParent(Article.class, articleId, Article.TAGS).byId(id)
				.uri(uriInfo).get();
	}

	@POST
	public SimpleResponse create(String data) {
		return LinkRest.create(Tag.class, config).toManyParent(Article.class, articleId, Article.TAGS).sync(data);
	}

	@PUT
	public SimpleResponse createOrUpdate(String data) {
		return LinkRest.createOrUpdate(Tag.class, config).toManyParent(Article.class, articleId, Article.TAGS)
				.sync(data);
	}

	@DELETE
	@Path("{tagId}")
	public SimpleResponse delete(@PathParam("articleId") int id) {
		return LinkRest.delete(Tag.class, config).toManyParent(Article.class, articleId, Article.TAGS).id(id).delete();
	}

}
