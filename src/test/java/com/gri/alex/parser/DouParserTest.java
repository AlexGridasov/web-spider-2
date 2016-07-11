package com.gri.alex.parser;

import com.gri.alex.WebSpider2Application;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Resource htmlResource = resourceLoader.getResource("article.html");
        File htmlFile = new File(htmlResource.getURI());
        doc = Jsoup.parse(htmlFile, "UTF-8", "https://dou.ua/lenta/interviews/it-career-0");
        doc.charset(Charset.forName("UTF-8"));
    }

    @Test
    public void testParsePageViews() throws Exception {
        long pageViews = douParser.parsePageViews(doc);
        assertEquals(2734, pageViews);
    }

    @Test
    public void testParseTitle() throws Exception {
        Elements articlesElements = doc.select("article");
        String title = douParser.parseTitle(articlesElements);
        assertEquals("Беседа с Алексеем Калиновским, CEO AgileEngine", title);
    }

    @Test
    public void testParseAnnouncement() throws Exception {

    }

    @Test
    public void testParseGuestPhoto() throws Exception {

    }

    @Test
    public void testParseContents() throws Exception {

    }

    @Test
    public void testParseBooks() throws Exception {

    }
}