package com.gri.alex.service;

import com.gri.alex.dao.BookRepository;
import com.gri.alex.dao.PodcastRepository;
import com.gri.alex.model.Book;
import com.gri.alex.model.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Alex on 10-Jul-165.
 */
@Service
public class PodcastService {

    @Autowired
    private PodcastRepository podcastRepository;
    @Autowired
    private BookRepository bookRepository;

    public PodcastService() {
    }


    public void savePodcast(Podcast podcast) {
        for (Book book : podcast.getBooks()) {
            bookRepository.save(book);
        }
        podcastRepository.save(podcast);
    }

    public Podcast findByNumber(long number) {
        return podcastRepository.findOne(number);
    }
}
