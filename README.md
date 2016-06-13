[![Build Status](https://travis-ci.org/andrus/linkrest-bootique.svg)](https://travis-ci.org/andrus/linkrest-bootique)

# bootique-linkrest-demo

A simple example of running a [LinkRest](http://linkrest.io) / [Apache Cayenne](http://cayenne.apache.org/) app
on [Bootique](http://bootique.io). Prerequisites:

* Java 1.8 or newer.
* Apache Maven.

Here is how to run it:

	git clone git@github.com:bootique-examples/bootique-linkrest-demo.git
	cd bootique-linkrest-demo
	mvn package
	java -jar target/bootique-linkrest-demo-1.0-SNAPSHOT.jar --server --config=run.yml

Resources:

	http://127.0.0.1:8080/domain
	http://127.0.0.1:8080/domain/{domainId}
	http://127.0.0.1:8080/domain/{domainId}/articles
	http://127.0.0.1:8080/domain/{domainId}/articles/{articleId}

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
