/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.util.Collection;
import org.lib.business.LibraryFacade;
import org.lib.business.LibraryFacadeInterface;
import org.lib.model.Book;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class GetAllBooks extends AbstractCommand<Collection<Book>> {

    @Override
    public Collection<Book> execute(LibraryFacadeInterface f) throws LibraryException {
        return f.getAllBooks();
    }

    @Override
    public String toString() {
        return "GetAllBooks{" + '}';
    }

}
