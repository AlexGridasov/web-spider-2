package com.gri.alex;

import com.gri.alex.controller.PodcastControllerImpl;
import com.gri.alex.model.Podcast;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 10-Jul-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebSpider2Application.class)
public class PodcastControllerImplTest {

    @Autowired
    private ResourceLoader resourceLoader;
    @Mock
    private PodcastControllerImpl podcastControllerImplMock;

    private Document doc;
    private byte[] image;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        Resource htmlResource = resourceLoader.getResource("article.html");
        File htmlFile = new File(htmlResource.getURI());
        doc = Jsoup.parse(htmlFile, "UTF-8", "https://dou.ua/lenta/interviews/it-career-0");

        Resource imageResource = resourceLoader.getResource("alex_kalinovsky.jpg");
        InputStream imageStream = null;
        try {
            imageStream = imageResource.getInputStream();
            image = IOUtils.toByteArray(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(imageStream);
        }
    }

    @Test
    public void getHtmlDocument() throws Exception {
        when(podcastControllerImplMock.getHtmlDocument(0))
                .thenReturn(doc);

        Document document = podcastControllerImplMock.getHtmlDocument(0);

        assertNotNull(document);
    }

    @Test
    public void getImage() {
        when(podcastControllerImplMock.getImage(""))
                .thenReturn(image);

        byte[] photo = podcastControllerImplMock.getImage("");

        assertNotNull(photo);
    }

    @Test
    public void parsePodcastHtml() throws Exception {
        Podcast podcast = new Podcast();
        when(podcastControllerImplMock.getPodcast(0))
        .thenReturn(new Podcast());

        Podcast podcast1 = podcastControllerImplMock.getPodcast(0);

        assertEquals(podcast, podcast1);
    }

}