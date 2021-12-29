package org.WikiRelations.core;

import java.io.IOException;
import java.util.ArrayList;

public class WorkerThread implements Runnable {
    boolean finished = false;
    String originalLink;
    ArrayList<String> links;
    ArrayList<String> circularLinks;
    Integer id;

    public boolean isFinished() {
        return finished;
    }

    public WorkerThread(Integer id, String originalLink, ArrayList<String> links, ArrayList<String> circularLinks) {
        this.originalLink = originalLink;
        this.id = id;
        this.links = links;
        this.circularLinks = circularLinks;
    }


    public void run() {
        ArrayList<String> circularLinksList = null;
        try {
            circularLinksList = CircularLinksAlgorithm.checkAllSubListLinks(originalLink, links);
        } catch (IOException e) {
            e.printStackTrace();
        }
        circularLinks.addAll(circularLinksList);
        this.finished = true;
    }
}
