package com.gri.alex.dao;

import com.gri.alex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by Alex on 09-Jul-16.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Collection<Book> findByPodcastId(Long id);
}
