/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.lib.integration.BookDAO;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class DefaultBookDAO implements BookDAO {

    public DefaultBookDAO() {

    }

    Map<BookId, Book> booksMap = new HashMap<>();
    private static int idCounter;

    @Override
    public void create(String title, String author) {
        BookId id = new BookId(++idCounter);
        booksMap.put(id, new Book(id, title, author));
    }

    @Override
    public Collection<Book> getAll() {
        return new ArrayList<>(booksMap.values());
    }

    @Override
    public void delete(BookId bookId) throws LibraryException {
        booksMap.remove(bookId);
    }

}
