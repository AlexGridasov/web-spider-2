package com.gri.alex.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Alex on 08/07/2016.
 */
@Entity
@Table(name = "PODCAST")
public class Podcast {

    @Column(name = "NUMBER", unique = true, nullable = false)
    private Integer number;

    @Column(name = "TITLE", unique = true, nullable = false)
    private String title;

    @Column(name = "CONTENTS", unique = true, nullable = false)
    private String contents;

    @OneToMany
    @JoinColumn(name = "")
    private Set<Book> books = new HashSet<>();

    public Podcast() {
    }

    public Podcast(Integer number, String title, String contents) {
        this.number = number;
        this.title = title;
        this.contents = contents;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Podcast)) return false;

        Podcast other = (Podcast) obj;
        if (!Objects.equals(number, other.number)) return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Podcast{");
        sb.append("title='").append(title).append('\'');
        sb.append(",\n contents=").append(contents);
        sb.append(",\n books=").append(books);
        sb.append('}');
        return sb.toString();
    }
}
