package com.gri.alex.parser;

import com.gri.alex.model.Book;
import com.gri.alex.model.Podcast;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 09-Jul-16.
 */
@Component
public class DouParser {

    private static final int MAX_LINK_SIZE = 300;
    private static final Logger LOGGER = Logger.getLogger(DouParser.class);
    public static final String HTTPS_PREFIX = "https:";

    public Podcast parseDocument(Document doc) {
        Podcast podcast = new Podcast();
        Elements articleElements = doc.select("article");

        if (articleElements != null && articleElements.size() > 0) {
            long pageViews = parsePageViews(doc);
            podcast.setPageViews(pageViews);

            String title = parseTitle(articleElements);
            podcast.setTitle(title);

            String announcement = parseAnnouncement(articleElements);
            podcast.setAnnouncement(announcement);

            String guestPhoto = parseGuestPhoto(articleElements);
            podcast.setPhotoLink(guestPhoto);

            Elements lists = articleElements.select("ul");
            if (lists.size() > 0) {
                // contents
                List<String> contents = parseContents(lists.get(0));
                podcast.setContents(StringUtils.collectionToDelimitedString(contents, "||"));

                if (lists.size() > 1) {
                    // books
                    Set<Book> bookSet = parseBooks(lists.get(1), "li");
                    podcast.setBooks(bookSet);
                }
            }
        }

        return podcast;
    }

    public long parsePageViews(Document doc) {
        Elements spanElements = doc.select("span[title=Количество просмотров].pageviews");
        Element spanElement = spanElements.get(0);
        long pageViews = Long.valueOf(spanElement.text());
        LOGGER.info("Количество просмотров: " + pageViews);

        return pageViews;
    }

    public String parseTitle(Elements elements) {
        Elements articleTitle = elements.select("h1");
        String title = articleTitle.text();
        LOGGER.info("Заголовок : " + title);

        return title;
    }

    public String parseAnnouncement(Elements elements) {
        Element announcementElement = elements.select("p").first();
        String announcement = announcementElement.text();
        LOGGER.info("Анонс : " + announcement);

        return announcement;
    }

    public String parseGuestPhoto(Elements elements) {
        Element guestPhotoElement = elements.select("img").first();
        String guestPhoto = guestPhotoElement.attr("src");
        if (!guestPhoto.contains(HTTPS_PREFIX)) {
            guestPhoto = "https:" + guestPhoto;
        }
        LOGGER.info("Фото : " + guestPhoto);

        return guestPhoto;
    }

    public List<String> parseContents(Element element) {
        Elements themes = element.select("li");
        List<String> themeList = new ArrayList<>();

        for (Element theme : themes) {
            themeList.add(theme.text());
        }
        LOGGER.info("Содержание : " + themeList.size());

        return themeList;
    }

    public Set<Book> parseBooks(Element element, String selector) {
        Elements books = element.select(selector);
        Set<Book> bookList = new HashSet<>();

        for (Element book : books) {
            String bookTitle = book.text();
            String bookLink = book.select("a").attr("href");

            if (bookLink.length() > MAX_LINK_SIZE) {
                LOGGER.warn(bookLink);
                bookLink = bookLink.substring(1, MAX_LINK_SIZE);
            }

            bookList.add(new Book(bookTitle, bookLink));
        }
        LOGGER.info("Книги : " + bookList.size());

        return bookList;
    }
}
