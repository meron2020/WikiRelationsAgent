package org.WikiRelations;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.WikiRelations.core.LinkThreadList;
import org.WikiRelations.resources.LinksResource;

public class WikiRelationsAgent extends Application<WikiRelationsAgentConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WikiRelationsAgent().run(args);
    }

    @Override
    public String getName() {
        return "WikiRelations";
    }

    @Override
    public void initialize(final Bootstrap<WikiRelationsAgentConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final WikiRelationsAgentConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        LinkThreadList linkThreadList = new LinkThreadList();
        environment.jersey().register(new LinksResource(linkThreadList));


    }

}
