package com.gri.alex;

import com.gri.alex.controller.PodcastController;
import com.gri.alex.model.Podcast;
import com.gri.alex.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Alex on 08-Jul-16.
 */
@SpringBootApplication
@EnableScheduling
public class WebSpider2Application {

    @Autowired
    private static PodcastController podcastController;
    @Autowired
    private static PodcastService podcastService;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebSpider2Application.class, args);
        podcastController = ctx.getBean("podcastController", PodcastController.class);
        podcastService = ctx.getBean("podcastService", PodcastService.class);

        Long podcastNumber = 259L;
        Podcast podcast = podcastController.getPodcastByNumber(podcastNumber);

        podcastService.savePodcast(podcast);
    }
}
