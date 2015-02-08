/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import java.io.Serializable;

/**
 *
 * @author danecek
 */
public class Borrow implements Serializable {

    private final BookId bookId;
    private final LibReaderId readerId;

    public Borrow(BookId bookId, LibReaderId readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    /**
     * @return the bookId
     */
    public BookId getBookId() {
        return bookId;
    }

    /**
     * @return the readerId
     */
    public LibReaderId getReaderId() {
        return readerId;
    }

}
