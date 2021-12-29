package org.WikiRelations.resources;

import org.WikiRelations.core.LinkThreadList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/communication")
public class HeadMasterCommsResource {
    LinkThreadList linkThreadList = new LinkThreadList();

    public HeadMasterCommsResource(LinkThreadList linkThreadList) {
        this.linkThreadList = linkThreadList;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getRankingListSize() {
        return this.linkThreadList.getLinkThreadsSize();
    }

}
