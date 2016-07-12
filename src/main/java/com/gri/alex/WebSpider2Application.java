package com.gri.alex;

import com.gri.alex.controller.PodcastController;
import com.gri.alex.controller.PodcastControllerImpl;
import com.gri.alex.model.Podcast;
import com.gri.alex.service.PodcastService;
import com.gri.alex.service.PodcastServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by Alex on 08-Jul-16.
 */
@SpringBootApplication
public class WebSpider2Application {

    @Autowired
    private static PodcastController podcastController;
    @Autowired
    private static PodcastService podcastService;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebSpider2Application.class, args);
        podcastController = ctx.getBean("podcastControllerImpl", PodcastControllerImpl.class);
        podcastService = ctx.getBean("podcastServiceImpl", PodcastServiceImpl.class);

        Podcast podcast = podcastController.getPodcast(259L);
        podcastService.savePodcast(podcast);
    }
}
