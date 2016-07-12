package com.gri.alex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Alex on 08-Jul-16.
 */
@Entity
@Table(name = "BOOK")
public class Book {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "LINK", nullable = false, length = 300)
    private String link;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "NUMBER")
    private Podcast podcast;


    public Book() {
    }

    public Book(String title, String link) {
        this.title = title;
        this.link = link;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("title='").append(title).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        Book obj2 = (Book) obj;
        if (this.title.equals(obj2.getTitle())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return title.hashCode();
    }

}
