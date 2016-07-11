package com.gri.alex.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Alex on 08-Jul-16.
 */
@Entity
@Table(name = "PODCAST")
public class Podcast {

    @Id
    @Column(name = "NUMBER", unique = true, nullable = false)
    private Long number;

    @Column(name = "PAGE_VIEWS")
    private Long pageViews;

    @Column(name = "TITLE", unique = true, nullable = false)
    private String title;

    @Column(name = "ANNOUNCEMENT", unique = true, nullable = false)
    private String announcement;

    @Column(name = "CONTENTS", unique = true, nullable = false)
    private String contents;

    @Column(name = "GUEST_PHOTO")
    private Blob guestPhoto;

    @OneToMany(mappedBy = "podcast")
    private Set<Book> books = new HashSet<>();


    public Podcast() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getPageViews() {
        return pageViews;
    }

    public void setPageViews(Long pageViews) {
        this.pageViews = pageViews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Blob getGuestPhoto() {
        return guestPhoto;
    }

    public void setGuestPhoto(Blob guestPhoto) {
        this.guestPhoto = guestPhoto;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + number);

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Podcast)) return false;

        Podcast other = (Podcast) obj;
        return Objects.equals(number, other.number);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Podcast{")
                .append("number='").append(number).append('\'')
                .append(",\n pageViews=").append(pageViews)
                .append(",\n title=").append(title)
                .append(",\n announcement=").append(announcement)
                .append(",\n contents=").append(contents)
                .append(",\n books=").append(books)
                .append('}');

        return sb.toString();
    }
}
