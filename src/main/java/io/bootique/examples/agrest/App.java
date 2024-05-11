package io.bootique.examples.agrest;

import io.bootique.BQModule;
import io.bootique.Bootique;
import io.bootique.agrest.v5.swagger.AgrestSwaggerModule;
import io.bootique.di.Binder;
import io.bootique.examples.agrest.api.DomainResource;
import io.bootique.examples.agrest.cayenne.Domain;
import io.bootique.jersey.JerseyModule;

import java.awt.print.Book;

/**
 * A runnable Bootique + Agrest + Cayenne application.
 */
public class App implements BQModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(App.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        JerseyModule.extend(binder).addResource(DomainResource.class);
        AgrestSwaggerModule.extend(binder).entityPackage(Domain.class);
    }
}
