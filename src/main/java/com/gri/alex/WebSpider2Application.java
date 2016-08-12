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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        savePodcastsFromFile();
    }

    private static void savePodcastsFromFile() {
        List<Podcast> podcasts =  podcastController.parsePodcastsFromFile();
        for (Podcast podcast: podcasts) {
            podcastService.savePodcast(podcast);
        }
    }

    private static void savePodcastsFromWeb() {
        List<Long> ignoreList = new ArrayList<>(Arrays.asList(59L, 89L, 92L, 99L, 149L, 253L));

        // 43 first, 267 last
        // ignore: 59, 89, 92, 99, 149, 253,
        for (long index = 43; index <= 267; index++) {
            if (!ignoreList.contains(index)) {
                Long podcastNumber = index;
                Podcast podcast = podcastController.createPodcastByNumber(podcastNumber);

                podcastService.savePodcast(podcast);
            }
        }
    }
}
