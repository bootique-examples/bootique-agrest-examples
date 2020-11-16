[![Build Status](https://travis-ci.org/bootique-examples/bootique-agrest-demo.svg)](https://travis-ci.org/bootique-examples/bootique-linkrest-demo)

# bootique-agrest-demo

A simple example of running a [Agrest](http://agrest.io) / [Apache Cayenne](http://cayenne.apache.org/) app
on [Bootique](http://bootique.io).

*For additional help/questions about this demo send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*

Prerequisites:

* Java 1.8 or newer.
* Apache Maven.

Here is how to run it:

	git clone git@github.com:bootique-examples/bootique-agrest-demo.git
	cd bootique-agrest-demo
	mvn package
	java -jar target/bootique-agrest-demo-2.0.jar --server --config=run.yml

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
         -d '{"id":1, "name":"My Site about Agrest"}'

    curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles'
    curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles?include=domain'

    curl -i -X POST 'http://127.0.0.1:8080/domain/1/articles' \
            -d '[
                 {"title":"Agrest Presentation","body":"Here is how to use Agrest"},
                 {"title":"Cayenne Goodies", "body":"This is an article about Apache Cayenne"}
                ]'
             
    curl -i -X PUT 'http://127.0.0.1:8080/domain/1/articles' \
         -d '{"id":1,"title":"Agrest latest Presentation"}'
         
    curl -i -X DELETE 'http://127.0.0.1:8080/domain/1/articles/1'
