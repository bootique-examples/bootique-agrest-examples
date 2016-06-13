package org.objectstyle.bootique.linkrest.cayenne;

import java.util.Date;

import org.objectstyle.bootique.linkrest.cayenne.auto._Article;

public class Article extends _Article {

    private static final long serialVersionUID = 1L; 

    @Override
    protected void onPostAdd() {
		setPublishedOn(new Date());
    }

}
