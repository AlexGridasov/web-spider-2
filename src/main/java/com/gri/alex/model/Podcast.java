package com.gri.alex.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Column(name = "ANNOUNCEMENT", unique = true)
    private String announcement;

    @Column(name = "CONTENTS", unique = true, nullable = false, length = 1000)
    private String contents;

    @Column(name = "PHOTO_LINK")
    private String photoLink;

    @OneToMany(mappedBy = "podcast", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public Podcast() {
    }

    /*public void updateGuestPhoto(final byte[] photo, final String photoLink) {
        if (guestPhoto == null) {
            guestPhoto = new GuestPhoto();
        }
        guestPhoto.update(photo, photoLink);
    }*/

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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        for(Book book : books) {
            this.books.add(book);
            if (book.getPodcast() != this) {
                book.setPodcast(this);
            }
        }
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
