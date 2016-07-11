package com.gri.alex;

import com.gri.alex.model.Podcast;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 10-Jul-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebSpider2Application.class)
public class PodcastControllerTest {

    @Autowired
    private ResourceLoader resourceLoader;
    @Mock
    private PodcastController podcastController;

    private Document doc;
    private Image image;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        Resource htmlResource = resourceLoader.getResource("article.html");
        doc = new Document(htmlResource.getURI().getPath());

        Resource imageResource = resourceLoader.getResource("alex_kalinovsky.jpg");
        image = ImageIO.read(imageResource.getInputStream());
    }

    @Test
    public void getHtmlDocument() throws Exception {
        when(podcastController.getHtmlDocument(0))
                .thenReturn(doc);

        Document document = podcastController.getHtmlDocument(0);

        assertNotNull(document);
    }

    @Test
    public void getImage() {
        when(podcastController.getImage(""))
                .thenReturn(image);

        Image photo = podcastController.getImage("");

        assertNotNull(photo);
    }

    @Test
    public void parsePodcastHtml() throws Exception {
        Podcast podcast = new Podcast();
        when(podcastController.getPodcast(0))
                .thenReturn(new Podcast());

        Podcast podcast1 = podcastController.getPodcast(0);

        assertEquals(podcast, podcast1);
    }

}