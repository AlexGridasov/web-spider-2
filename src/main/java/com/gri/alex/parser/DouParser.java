package com.gri.alex.parser;

import com.gri.alex.model.Book;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 09-Jul-16.
 */
@Component
public class DouParser implements Parser {

    private static final int MAX_LINK_SIZE = 300;
    private static final Logger LOGGER = Logger.getLogger(DouParser.class);

    public long parsePageViews(Document doc) {
        Elements spanElements = doc.select("span[title=Количество просмотров].pageviews");
        Element spanElement = spanElements.get(0);
        return Long.valueOf(spanElement.text());
    }

    public String parseTitle(Elements elements) {
        Elements articleTitle = elements.select("h1");
        return articleTitle.text();
    }

    public String parseAnnouncement(Elements elements) {
        Element announcement = elements.select("p").first();
        return announcement.text();
    }

    public String parseGuestPhoto(Elements elements) {
        Element guestPhoto = elements.select("p").first()
                                     .select("img").first();
        return guestPhoto.attr("src");
    }

    public List<String> parseContents(Element element) {
        Elements themes = element.select("li");
        List<String> themeList = new ArrayList<>();

        for (Element theme : themes) {
            themeList.add(theme.text());
        }

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

        return bookList;
    }
}
