CREATE TABLE "domain" ("id" bigserial NOT NULL, "name" varchar(100) NOT NULL, "vhost" varchar(100) NOT NULL, PRIMARY KEY ("id"));
CREATE TABLE "article" ("body" text NULL, "domain_id" bigint NOT NULL, "id" bigserial NOT NULL, "published_on" timestamp with time zone NOT NULL, "title" varchar(100) NOT NULL, PRIMARY KEY ("id"));
CREATE TABLE "tag" ("article_id" bigint NOT NULL, "id" bigserial NOT NULL, "name" varchar(200) NULL, PRIMARY KEY ("id"));

ALTER TABLE "article" ADD FOREIGN KEY ("domain_id") REFERENCES "domain" ("id");
ALTER TABLE "tag" ADD FOREIGN KEY ("article_id") REFERENCES "article" ("id");

CREATE SEQUENCE "pk_domain" INCREMENT 20 START 200;
CREATE SEQUENCE "pk_tag" INCREMENT 20 START 200;
CREATE SEQUENCE "pk_article" INCREMENT 20 START 200;
