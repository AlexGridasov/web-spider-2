package com.gri.alex;

import com.gri.alex.model.Podcast;
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

    private PodcastController controller;

    @Autowired
    public WebSpiderController(PodcastController controller) {
        this.controller = controller;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Podcast createPodcast(@RequestParam(value = "id", defaultValue = "259") String id, Model model) {

        return controller.getPodcast(259);
    }

    @RequestMapping("/")
    public String index() {

        return "Greetings from Web Spider!";
    }
}
