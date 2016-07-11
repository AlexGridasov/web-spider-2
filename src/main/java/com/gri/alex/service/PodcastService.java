package com.gri.alex.service;

import com.gri.alex.model.Podcast;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alex on 10-Jul-16.
 */
public interface PodcastService {

    void savePodcast(Podcast podcast);

    void deletePodcast(Podcast podcast);

    Optional<Podcast> findById(int id);

}
