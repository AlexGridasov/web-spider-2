package com.gri.alex;

import com.gri.alex.controller.PodcastController;
import com.gri.alex.model.Podcast;
import com.gri.alex.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 13/7/16.
 */
@Component
public class ScheduledTasks {

    @Autowired
    private PodcastService podcastService;
    @Autowired
    private PodcastController podcastController;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }

//    @Scheduled(fixedRate = 2000)
    public void checkNewPodcast() {
        //TODO: get next Podcast number
        Long podcastNumber = 0L;
        Podcast podcast = podcastController.createPodcastByNumber(podcastNumber);

        podcastService.savePodcast(podcast);
    }
}
