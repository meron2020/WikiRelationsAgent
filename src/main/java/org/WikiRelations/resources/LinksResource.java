package org.WikiRelations.resources;

import org.WikiRelations.core.LinkThreadList;
import org.WikiRelations.core.LinksObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


@Path("/links")
public class LinksResource {
    LinkThreadList linkThreadList;

    public LinksResource(LinkThreadList linkThreadList) {
        this.linkThreadList = linkThreadList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postLinks(LinksObject linksObject) throws IOException {
        System.out.println(linksObject.getLinks());
        System.out.println(linksObject.getOriginalLink());
        this.linkThreadList.addLinkThread(linksObject);
        return "Links added successfully";
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Hashtable<String, Object>> getFinishedLinks() {
        return this.linkThreadList.getAllFinishedLinks();
    }
}
