package com.gri.alex.service;

import com.gri.alex.WebSpider2Application;
import com.gri.alex.controller.PodcastController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
import java.io.IOException;

/**
 * Created by Alex on 13-Jul-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebSpider2Application.class)
public class PodcastServiceTest {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private PodcastService podcastService;
    @Autowired
    private PodcastController podcastController;

    private Document doc;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        Resource htmlResource = resourceLoader.getResource("article.html");
        File htmlFile = new File(htmlResource.getURI());
        doc = Jsoup.parse(htmlFile, "UTF-8", "https://dou.ua/lenta/interviews/it-career-0");
    }

    @Test
    public void testSavePodcast() throws Exception {
        /*when(podcastController.getHtmlDocument(0))
                .thenReturn(doc);

        Document document = podcastController.getHtmlDocument(0);*/
    }
}