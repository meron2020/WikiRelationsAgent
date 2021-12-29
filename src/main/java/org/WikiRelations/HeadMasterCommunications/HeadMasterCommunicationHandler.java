package org.WikiRelations.HeadMasterCommunications;


import org.WikiRelations.core.LinkThreadList;

import java.io.IOException;
import java.util.HashMap;

public class HeadMasterCommunicationHandler {
    LinkThreadList linkThreadList;

    public HeadMasterCommunicationHandler(LinkThreadList linkThreadList) {
        this.linkThreadList = linkThreadList;
    }

    public void sendHeadMasterAgentInfo(String port) throws IOException, InterruptedException {
        HashMap<String, Object> dataHashMap = new HashMap<>();
        dataHashMap.put("type", "links");
        dataHashMap.put("port", port);

        HTTPRequestsClass.sendPOSTRequest(dataHashMap);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LinkThreadList linkThreadList = new LinkThreadList();
        HeadMasterCommunicationHandler headMasterCommunicationHandler = new HeadMasterCommunicationHandler(linkThreadList);
        headMasterCommunicationHandler.sendHeadMasterAgentInfo("8090");
    }

}
