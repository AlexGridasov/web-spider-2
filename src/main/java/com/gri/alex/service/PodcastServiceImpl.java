package com.gri.alex.service;

import com.gri.alex.dao.PodcastRepository;
import com.gri.alex.model.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Created by Alex on 10-Jul-165.
 */
@Service
public class PodcastServiceImpl implements PodcastService {

    private PodcastRepository repository;

    @Autowired
    public PodcastServiceImpl(PodcastRepository repository) {
        this.repository = repository;
    }


    public void savePodcast(Podcast podcast) {
//        repository.savePodcast(podcast);
    }

    public void deletePodcast(Podcast podcast) {
//        repository.deletePodcast(podcast);
    }

    public Optional<Podcast> findById(int number) {
        return repository.findByNumber(number);
    }
}
