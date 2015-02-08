package org.lib.model;

import java.io.Serializable;

public class Book implements Serializable {

    static final long serialVersionUID = 42L;

    private final BookId id;
    private final String title;
    private final String author;

    public Book(BookId id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id.getId() + ", title=" + title + "}";
    }

    /**
     * @return the id
     */
    public BookId getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

}
