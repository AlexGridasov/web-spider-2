package com.gri.alex.controller;

import com.gri.alex.model.Podcast;
import com.gri.alex.parser.DouParser;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Alex on 09-Jul-16.
 */
@Component
public class PodcastController {

    final static Logger LOGGER = Logger.getLogger(PodcastController.class);
    final static String PODCAST_URL = "https://dou.ua/lenta/interviews/it-career-";
    public static final String HTTPS_PREFIX = "https:";

    @Autowired
    private DouParser douParser;
    @Autowired
    private ResourceLoader resourceLoader;

    public PodcastController() {
    }

    public List<Podcast> parsePodcastsFromFile() {
        Document doc = getHtmlDocumentFromFile("articles.html");
        List<Podcast> podcasts = douParser.parseDocument(doc);
        return podcasts;
    }

    private Document getHtmlDocumentFromFile(String name) {
        Document doc = null;
        try {
            Resource resource = resourceLoader.getResource("classpath:" + name);
            File input = resource.getFile();
            doc = Jsoup.parse(input, "UTF-8", "https://dou.ua");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public Podcast createPodcastByNumber(long podcastNumber) {
        Document doc = getHtmlDocument(podcastNumber);
        Podcast podcast = douParser.parseDocument(doc).get(0);
//        podcast.setNumber(podcastNumber);

        return podcast;
    }

    public Document getHtmlDocument(long podcastNumber) {
        Document doc = null;
        try {
            // need http protocol
            doc = Jsoup
                    .connect(PODCAST_URL + podcastNumber)
                    .userAgent("Chrome")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public byte[] getImage(String guestPhoto) {
        InputStream imageStream = null;
        byte[] image = null;
        if (!guestPhoto.startsWith(HTTPS_PREFIX)) {
            guestPhoto = HTTPS_PREFIX + guestPhoto;
        }

        try {
            imageStream = new URL(guestPhoto).openStream();
            image = IOUtils.toByteArray(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(imageStream);
        }

        return image;
    }

}
