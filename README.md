[![Build Status](https://travis-ci.org/andrus/linkrest-bootique.svg)](https://travis-ci.org/andrus/linkrest-bootique)

# linkrest-bootique

A simple example of running a [LinkRest](http://nhl.github.io/link-rest/) app on [Bootique](http://bootique.io). Prerequisites:

* Java 1.8 or newer.
* Apache Maven.

Here is how to run it:

	git clone https://github.com/andrus/linkrest-bootique.git
	cd linkrest-bootique
	mvn package
	java -jar target/linkrest-bootique-1.0-SNAPSHOT.jar --server --config=run.yml

That's it, no need for installing Tomcat, doing deployment, etc. That's the beauty of Bootique. LinkRest bootstrap part happens in the [BQApplication class](https://github.com/andrus/linkrest-bootique/blob/master/src/main/java/org/objectstyle/linkrest/cms/BQApplication.java).

Resources:

	/domain
	/domain/{domainId}
	/domain/{domainId}/articles
	/domain/{domainId}/articles/{articleId}

Sample Operations:

    curl -i -X POST 'http://127.0.0.1:8080/domain' \
         -d '{"vhost":"mysite1.example.org","name":"My Site #1"}'
         
    curl -i -X GET 'http://127.0.0.1:8080/domain'
    
    # query String is a URL encoded form of 
    # 'include={"path":"articles","sort":"publishedOn"}&exclude=articles.body'
    curl -i -X GET  'http://127.0.0.1:8080/domain?include=%7B%22path%22%3A%22articles%22%2C%22sort%22%3A%22publishedOn%22%7D&exclude=articles.body'
         
    curl -i -X PUT 'http://127.0.0.1:8080/domain' \
         -d '{"id":1, "name":"My Site about LinkRest"}'

    curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles'
    curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles?include=domain'
    
    curl -i -X POST 'http://127.0.0.1:8080/domain/1/articles' \
         -d '[
              {"title":"LinkRest Presentation","body":"Here is how to use LinkRest"},
              {"title":"Cayenne Goodies", "body":"This is an article about Apache Cayenne"}
             ]'
             
    curl -i -X PUT 'http://127.0.0.1:8080/domain/1/articles' \
         -d '{"id":1,"title":"LinkRest latest Presentation"}'
         
    curl -i -X DELETE 'http://127.0.0.1:8080/domain/1/articles/1'
