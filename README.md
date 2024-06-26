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

## Start Postgres DB Locally

This starts a Postgres instance listening on port 15432, with login credentials of `postgres` / `test`, and
creates a simple test schema:

```bash
docker-compose -f docker-compose.yml up -d
```

## Build and package

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

Since the example includes OpenAPI / Swagger integration, now you can see the available endpoints by going to
http://127.0.0.1:8080/swagger-ui URL. 

You can run the following operations to create, update and read the test data. You can run them from the command
line, or from the Swagger console in the browser.

``` 
curl -i -X POST 'http://127.0.0.1:8080/domain' \
    -d '{"vhost":"mysite1.example.org","name":"My Site #1"}'
         
curl -i -X GET 'http://127.0.0.1:8080/domain'
    
# This query String is a URL-encoded form of 
# 'include={"path":"articles","sort":"publishedOn"}&exclude=articles.body'
curl -i -X GET  'http://127.0.0.1:8080/domain?include=%7B%22path%22%3A%22articles%22%2C%22sort%22%3A%22publishedOn%22%7D&exclude=articles.body'
         
curl -i -X PUT 'http://127.0.0.1:8080/domain' \
    -d '{"id":1, "name":"My Site about Agrest"}'

curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles?include=domain'

curl -i -X POST 'http://127.0.0.1:8080/domain/1/articles' \
    -d '[
                 {"title":"Agrest Presentation","body":"Here is how to use Agrest"},
                 {"title":"Cayenne Goodies", "body":"This is an article about Apache Cayenne"}
    ]'
    
curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles?include=domain'
             
curl -i -X PUT 'http://127.0.0.1:8080/domain/1/articles' \
    -d '{"id":1,"title":"Agrest latest Presentation"}'
     
curl -i -X GET 'http://127.0.0.1:8080/domain/1/articles?include=domain'
    
curl -i -X DELETE 'http://127.0.0.1:8080/domain/1/articles/1'
```