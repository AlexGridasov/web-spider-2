package com.gri.alex.parser;

import com.gri.alex.WebSpider2Application;
import com.gri.alex.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 11-Jul-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebSpider2Application.class)
public class DouParserTest {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private DouParser douParser;

    private Document doc;
    private Elements articlesElements;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Resource htmlResource = resourceLoader.getResource("article.html");
        File htmlFile = new File(htmlResource.getURI());
        doc = Jsoup.parse(htmlFile, "UTF-8", "https://dou.ua/lenta/interviews/it-career-0");
        doc.charset(Charset.forName("UTF-8"));

        articlesElements = doc.select("article");
    }

    @Test
    public void testParsePageViews() throws Exception {
        long pageViews = douParser.parsePageViews(doc, i);
        assertEquals(2734, pageViews);
    }

    @Test
    public void testParseTitle() throws Exception {
        String title = douParser.parseTitle(articlesElements);
        assertEquals("Беседа с Алексеем Калиновским, CEO AgileEngine", title);
    }

    @Test
    public void testParseAnnouncement() throws Exception {
        String announcement = douParser.parseAnnouncement(articlesElements);
        assertEquals("259-й выпуск подкаста «Откровенно про IT карьеризм». В подкасте пойдет речь о программировании и предпринимательстве.", announcement);
    }

    @Test
    public void testParseGuestPhoto() throws Exception {
        String guestPhoto = douParser.parseGuestPhoto(articlesElements);
        assertEquals("https://s.dou.ua/img/announces/q_udshTEd.jpg", guestPhoto);
    }

    @Test
    public void testParseContents() throws Exception {
        List<String> contentsExpected = new ArrayList<>(
                Arrays.asList("Про во’IT’и", "Первая компания", "Уезд из страны",
                        "Работа консультантом", "Любимый и нелюбимый проект",
                        "Карьера менеджера", "Написание книги", "Первый продукт AjaxSwing",
                        "Второй продукт Screenster", "Аутсорсинг", "Тайм менеджмент",
                        "Вдохновение", "Разные точки зрения",
                        "Разработчики, менеджеры и предприниматели"));

        Elements lists = articlesElements.select("ul");
        Element contentsElement = lists.get(0);
        List<String> contents = douParser.parseContents(contentsElement);

        assertEquals(contentsExpected, contents);
    }

    @Test
    public void testParseBooks() throws Exception {
        Set<Book> booksExpected = new HashSet<>();
        Book book1 = new Book();
        book1.setTitle("Как завоевывать друзей и оказывать влияние на людей — Дейл Карнеги");
        book1.setLink("http://amzn.to/27R58cA");
        Book book2 = new Book();
        book2.setTitle("Pitch Anything: An Innovative Method for Presenting, Persuading, and Winning the Deal — Oren Klaff");
        book2.setLink("http://amzn.to/1sNHNZA");
        booksExpected.add(book1);
        booksExpected.add(book2);

        Elements lists = articlesElements.select("ul");
        Element booksElement = lists.get(1);
        Set<Book> bookSet = douParser.parseBooks(booksElement, "li");

        assertEquals(booksExpected, bookSet);
    }
}