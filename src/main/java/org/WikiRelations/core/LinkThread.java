package org.WikiRelations.core;

import java.io.IOException;
import java.util.ArrayList;

public class LinkThread extends Thread{
    ArrayList<String> circularLinks;
    LinksObject linksObject;
    boolean finished = false;
    boolean started = false;

    public LinkThread(LinksObject linksObject) {
        this.linksObject = linksObject;
    }

    public void run() {
        this.started = true;
        try {
            this.circularLinks = CircularLinksAlgorithm.checkAllLinks( this.linksObject.getOriginalLink(),
                    this.linksObject.getLinks(), this.linksObject.getId());

            this.finished = true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCircularLinks() {
        return circularLinks;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isFinished() {
        return finished;
    }
}
