package com.gri.alex;

import com.gri.alex.controller.PodcastControllerImpl;
import com.gri.alex.model.Podcast;
import com.gri.alex.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alex on 09-Jul-16.
 */
@RestController
@RequestMapping("/podcast")
public class WebSpiderController {

    @Autowired
    private PodcastControllerImpl podcastController;
    @Autowired
    private PodcastService podcastService;

    public WebSpiderController() {
    }


    @RequestMapping(method = RequestMethod.GET)
    public Podcast getPodcast(@RequestParam(value = "id", defaultValue = "259") String id, Model model) {

//        return podcastController.getPodcast(259);
        return podcastService.findByNumber(259);
    }

    @RequestMapping("/")
    public String index() {

        return "Greetings from Web Spider!";
    }
}
