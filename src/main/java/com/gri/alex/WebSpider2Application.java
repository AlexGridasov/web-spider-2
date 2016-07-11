package com.gri.alex;

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
    private static PodcastController controller;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebSpider2Application.class, args);
        controller = (PodcastController) ctx.getBean("podcastController");
        controller.getPodcast(259);
    }
}
