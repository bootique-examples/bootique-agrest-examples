package io.bootique.agrest.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.agrest.demo.api.DomainResource;
import io.bootique.jersey.JerseyModule;

/**
 * A runnable Bootique + Agrest + Cayenne application.
 */
public class App implements Module {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(App.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        // add all classes in DomainResource's class package as REST API resources
        JerseyModule.extend(binder)
                .addPackage(DomainResource.class.getPackage());
    }
}
