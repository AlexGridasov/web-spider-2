package com.gri.alex.dao;

import com.gri.alex.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by Alex on 09-Jul-16.
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    Collection<Book> findByPodcastNumber(Long id);
}
