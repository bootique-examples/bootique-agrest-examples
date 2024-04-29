[![verify](https://github.com/bootique-examples/bootique-agrest-examples/actions/workflows/verify.yml/badge.svg)](https://github.com/bootique-examples/bootique-agrest-examples/actions/workflows/verify.yml)

# Bootique 3.x Agrest Examples

A simple example of running a [Agrest](http://agrest.io) / [Apache Cayenne](http://cayenne.apache.org/) app on [Bootique](http://bootique.io).


Different Git branches contain example code for different versions of Bootique:
* [3.x](https://github.com/bootique-examples/bootique-agrest-examples/tree/3.x)
* [2.x](https://github.com/bootique-examples/bootique-agrest-examples/tree/2.x)
* [1.x](https://github.com/bootique-examples/bootique-agrest-examples/tree/1.x)

## Prerequisites

To build and run the project, ensure you have the following installed on your machine:

* Docker
* Java 11 or newer
* Maven

and then follow these steps:

## Checkout
```
git clone git@github.com:bootique-examples/bootique-agrest-examples.git
cd bootique-agrest-examples
```

## Build, test and package

Run the following command to build the code, run the tests and package the app:
```
mvn clean package
```

## Run

The following command prints a help message with supported options:
```bash  
java -jar target/bootique-agrest-examples-3.0.jar
```

The following command starts the Agrest REST server with an embedded Derby DB:
```bash 
java -jar target/bootique-agrest-examples-3.0.jar --server --config=config.yml
```

The app exposes the following resources:

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
