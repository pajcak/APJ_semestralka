/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import java.util.Collection;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

public interface BookDAO {

    void create(String title, String author) throws LibraryException;

    Collection<Book> getAll() throws LibraryException;
    
    void delete(BookId bookId)  throws LibraryException;

}
