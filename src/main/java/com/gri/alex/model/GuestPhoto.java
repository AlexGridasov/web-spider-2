package com.gri.alex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by Alex on 13-Jul-16.
 */
@Embeddable
public class GuestPhoto {

    @JsonIgnore
    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "PHOTO_LINK")
    private String photoLink;


    public GuestPhoto() {
    }

    public void update(final byte[] photo, final String photoLink) {
        this.photo = photo;
        this.photoLink = photoLink;
    }

    public GuestPhoto(String photoLink) {
        this.photoLink = photoLink;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
