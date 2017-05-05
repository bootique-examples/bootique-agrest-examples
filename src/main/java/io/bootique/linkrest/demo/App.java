package io.bootique.linkrest.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;
import io.bootique.linkrest.demo.api.DomainResource;

/**
 * A runnable Bootique + LinkRest + Cayenne application.
 */
public class App implements Module {

    public static void main(String[] args) throws Exception {
        Bootique.app(args).module(App.class).autoLoadModules().run();
    }

    @Override
    public void configure(Binder binder) {

        // add all classes in DomainResource's class package as REST API resources
        JerseyModule.extend(binder).addPackage(DomainResource.class.getPackage());

    }
}
