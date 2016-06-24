package io.bootique.linkrest.demo.cayenne;

import java.util.Date;

import io.bootique.linkrest.demo.cayenne.auto._Article;

public class Article extends _Article {

    private static final long serialVersionUID = 1L; 

    @Override
    protected void onPostAdd() {
		setPublishedOn(new Date());
    }

}
