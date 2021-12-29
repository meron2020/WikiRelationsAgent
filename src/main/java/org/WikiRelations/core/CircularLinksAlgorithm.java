package org.WikiRelations.core;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CircularLinksAlgorithm {

    public static String createAPILink(String query) {
        return String.format("http://en.wikipedia.org/w/index.php?action=render&title=%s", query);
    }


    public static boolean checkIfLinkCircular(String originalQuery, String url)
            throws IOException {
        String[] urlParts = url.split("/");
        if (urlParts[urlParts.length-1].contains(":")) {
            return false;
        }

        try {
            Document doc = Jsoup.connect(url).get();
            Elements aElements = doc.select("a");
            for (Element a : aElements) {
                String aLink = a.attr("href");
                String[] linkParts = aLink.split("/");
                String link = "https://en.wikipedia.org" + aLink;
                if (link.equals(originalQuery)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public static String addApiLink(String query) {
        return String.format("http://en.wikipedia.org/w/index.php?action=render&title=%s", query);
    }

    public static ArrayList<String> checkAllSubListLinks(String originalQuery, ArrayList<String> links) throws IOException {
        ArrayList<String> circularLinks = new ArrayList<>();
        for (String link : links) {
            if (checkIfLinkCircular(originalQuery, link)) {
                circularLinks.add(link);
                System.out.println("[+] Checked if link circular: " + link);
            }


        }
        return circularLinks;
    }


    public static ArrayList<String> checkAllLinks(String originalQuery, ArrayList<String> queries, Integer id)
            throws IOException, InterruptedException {
        ArrayList<String> circularLinks = new ArrayList<>();
        ArrayList<ArrayList<String>> listOfQueries = ArrayListSplitter.split(50, queries);
        ArrayList<WorkerThread> workerThreads = new ArrayList<>();
        for (ArrayList<String> array : listOfQueries) {
            WorkerThread workerThread = new WorkerThread(id, originalQuery, array, circularLinks);
            workerThreads.add(workerThread);
            new Thread(workerThread).start();
//            workerThread.start(originalLink, array, circularLinks);
        }
        while (workerThreads.size() != 0) {
            workerThreads.removeIf(WorkerThread::isFinished);
        }

        System.out.println(queries.size());
        System.out.println(circularLinks.size());
        System.out.println("Finished checking all links");
        return circularLinks;
    }
}
