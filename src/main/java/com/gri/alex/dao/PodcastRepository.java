package com.gri.alex.dao;

import com.gri.alex.model.Podcast;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alex on 09-Jul-16.
 */
public interface PodcastRepository extends CrudRepository<Podcast, Long> {

//    Optional<Podcast> findByNumber(int number);

}
