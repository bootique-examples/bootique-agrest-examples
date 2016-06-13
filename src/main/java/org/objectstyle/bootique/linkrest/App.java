package org.objectstyle.bootique.linkrest;

import com.google.inject.Binder;
import org.objectstyle.bootique.linkrest.api.DomainResource;

import com.google.inject.Module;
import com.nhl.bootique.Bootique;
import com.nhl.bootique.jersey.JerseyModule;

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
        JerseyModule.contributePackages(binder).addBinding().toInstance(DomainResource.class.getPackage());
    }
}
