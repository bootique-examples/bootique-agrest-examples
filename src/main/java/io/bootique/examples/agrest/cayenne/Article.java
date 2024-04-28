package io.bootique.examples.agrest.cayenne;

import java.util.Date;

import io.bootique.examples.agrest.cayenne.auto._Article;

public class Article extends _Article {

    private static final long serialVersionUID = 1L; 

    @Override
    protected void onPostAdd() {
		setPublishedOn(new Date());
    }

}
