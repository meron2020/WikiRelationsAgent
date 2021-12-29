package org.WikiRelations.core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class LinkThreadList implements Runnable {
    public ArrayList<LinkThread> linkThreads = new ArrayList<>();

    public void addLinkThread(LinksObject linksObject) {
        LinkThread linkThread = new LinkThread(linksObject);
        this.linkThreads.add(linkThread);
        linkThread.start();
    }

    public void startAllLinkThreads() {
        for (LinkThread linkThread : linkThreads) {
            if (!linkThread.isStarted()) {
                linkThread.start();
            }
        }
    }

    public ArrayList<Hashtable<String, Object>> getAllFinishedLinks() {
        ArrayList<Hashtable<String, Object>> finished = new ArrayList<>();
        ArrayList<LinkThread> finishedThreads = new ArrayList<>();
        for (LinkThread linkThread : linkThreads) {
            if (linkThread.isFinished()) {
                finishedThreads.add(linkThread);
                Hashtable<String, Object> linksObject = new Hashtable<>();
                linksObject.put("Links", linkThread.getCircularLinks());
                linksObject.put("Original Link", linkThread.linksObject.getOriginalLink());
                linksObject.put("id", linkThread.linksObject.getId());
                finished.add(linksObject);
            }
        }

        for (LinkThread linkThread: finishedThreads) {
            linkThreads.remove(linkThread);
        }

        System.out.println(linkThreads.size());

        return finished;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < 10) {
            try {
                TimeUnit.SECONDS.sleep(5);
                startAllLinkThreads();
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
