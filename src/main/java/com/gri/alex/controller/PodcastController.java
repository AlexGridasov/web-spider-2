package com.gri.alex.controller;

import com.gri.alex.model.Podcast;
import com.gri.alex.parser.DouParser;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

    public PodcastController() {
    }

    public Podcast createPodcastByNumber(long podcastNumber) {
        LOGGER.info("Выпуск : " + podcastNumber);

        Document doc = getHtmlDocument(podcastNumber);
        Podcast podcast = douParser.parseDocument(doc);
        podcast.setNumber(podcastNumber);

//        String photoLink = podcast.getGuestPhoto().getPhotoLink();
//        podcast.updateGuestPhoto(getImage(photoLink), photoLink);

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
