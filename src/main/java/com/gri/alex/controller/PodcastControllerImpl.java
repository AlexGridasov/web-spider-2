package com.gri.alex.controller;

import com.gri.alex.model.Book;
import com.gri.alex.model.Podcast;
import com.gri.alex.parser.DouParser;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 09-Jul-16.
 */
@Component
public class PodcastControllerImpl implements PodcastController {

    final static Logger LOGGER = Logger.getLogger(PodcastControllerImpl.class);
    final static String PODCAST_URL = "https://dou.ua/lenta/interviews/it-career-";

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private DouParser douParser;

    public PodcastControllerImpl() {
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

    // TODO: split method
    public Podcast getPodcast(long podcastNumber) {
        Document doc = getHtmlDocument(podcastNumber);

        LOGGER.info("Выпуск : " + podcastNumber);

        Elements articlesElements = doc.select("article");

        if (articlesElements != null && articlesElements.size() > 0) {
            long pageViews = douParser.parsePageViews(doc);
            LOGGER.info("Количество просмотров: " + pageViews);

            String title = douParser.parseTitle(articlesElements);
            LOGGER.info("Заголовок : " + title);

            String announcement = douParser.parseAnnouncement(articlesElements);
            LOGGER.info("Анонс : " + announcement);

            String guestPhoto = douParser.parseGuestPhoto(articlesElements);
            LOGGER.info("Фото : " + guestPhoto);

            Elements lists = articlesElements.select("ul");

            if (lists.size() > 0) {
                // contents
                Element contentsElement = lists.get(0);
                List<String> contents = douParser.parseContents(contentsElement);
                LOGGER.info("Содержание : " + contents.size());

                if (lists.size() > 1) {
                    // books
                    Element booksElement = lists.get(1);
                    Set<Book> bookSet = douParser.parseBooks(booksElement, "li");
                    LOGGER.info("Книги : " + bookSet.size());

                    Podcast podcast = new Podcast();
                    podcast.setNumber(podcastNumber);
                    podcast.setPageViews(pageViews);
                    podcast.setTitle(title);
                    podcast.setAnnouncement(announcement);
//                    podcast.setGuestPhoto(getImage(guestPhoto));
                    podcast.setContents(StringUtils.collectionToDelimitedString(contents, "||"));
                    podcast.setBooks(bookSet);

                    return podcast;
                }
            }
        }

        return null;
    }

    public byte[] getImage(String guestPhoto) {
        InputStream imageStream = null;
        byte[] image = null;

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