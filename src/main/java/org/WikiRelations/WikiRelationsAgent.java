package org.WikiRelations;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.WikiRelations.HeadMasterCommunications.HeadMasterCommunicationHandler;
import org.WikiRelations.core.LinkThreadList;
import org.WikiRelations.resources.HeadMasterCommsResource;
import org.WikiRelations.resources.LinksResource;

import java.io.IOException;

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
                    final Environment environment) throws IOException, InterruptedException {
        // TODO: implement application
        LinkThreadList linkThreadList = new LinkThreadList();
        HeadMasterCommunicationHandler headMasterCommunicationHandler = new HeadMasterCommunicationHandler(linkThreadList);
        headMasterCommunicationHandler.sendHeadMasterAgentInfo("8090");
        environment.jersey().register(new LinksResource(linkThreadList));
        environment.jersey().register(new HeadMasterCommsResource(linkThreadList));

    }

}
