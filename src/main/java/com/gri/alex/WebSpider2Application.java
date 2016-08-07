package com.gri.alex;

import com.gri.alex.controller.PodcastController;
import com.gri.alex.model.Podcast;
import com.gri.alex.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Alex on 08-Jul-16.
 */
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class WebSpider2Application {

    @Autowired
    private static PodcastController podcastController;
    @Autowired
    private static PodcastService podcastService;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebSpider2Application.class, args);
        podcastController = ctx.getBean("podcastController", PodcastController.class);
        podcastService = ctx.getBean("podcastService", PodcastService.class);

        // 43 first, 267 last
        for (long index = 50; index <= 100; index++) {
            Long podcastNumber = index;
            Podcast podcast = podcastController.createPodcastByNumber(podcastNumber);

            podcastService.savePodcast(podcast);
        }
    }
}
