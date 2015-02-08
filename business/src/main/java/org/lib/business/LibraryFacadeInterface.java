/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Collection;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.LibReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface LibraryFacadeInterface {

    void borrowBooks(LibReaderId readerId, Collection<BookId> books) throws LibraryException;

    void createBook(String title, String author) throws LibraryException;

    void deleteBook(BookId id) throws LibraryException;

    Collection<Book> getAllBooks() throws LibraryException;
    
}
