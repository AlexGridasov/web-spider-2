package com.gri.alex.dao;

import com.gri.alex.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alex on 09-Jul-16.
 */
public interface PodcastRepository extends JpaRepository<Podcast, Long> {

    Optional<Podcast> findByNumber(int number);

}
