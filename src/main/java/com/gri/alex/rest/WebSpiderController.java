package com.gri.alex.rest;

import com.gri.alex.controller.PodcastController;
import com.gri.alex.model.Podcast;
import com.gri.alex.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alex on 09-Jul-16.
 */
@RestController
@RequestMapping("/podcast")
public class WebSpiderController {

    @Autowired
    private PodcastController podcastController;
    @Autowired
    private PodcastService podcastService;

    public WebSpiderController() {
    }


    @RequestMapping(value = "/parser/{number}", method = RequestMethod.GET)
    public Podcast parsePodcast(@PathVariable(value = "number") Long number, Model model) {

        return podcastController.createPodcastByNumber(number);
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public Podcast getPodcast(@PathVariable(value = "number") Long number, Model model) {

        return podcastService.findByNumber(number);
    }

    @RequestMapping("/hi")
    public String index() {

        return "Greetings from Web Spider!";
    }
}
