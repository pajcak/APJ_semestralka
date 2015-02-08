/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import org.lib.business.LibraryFacade;
import org.lib.business.LibraryFacadeInterface;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class DeleteBook extends AbstractCommand<String> {

    private final BookId id;

    public DeleteBook(BookId id) {
        this.id = id;
    }

    @Override
    public String execute(LibraryFacadeInterface f) throws LibraryException {
        f.deleteBook(id);
        return OK;
    }

    @Override
    public String toString() {
        return "DeleteBook{" + id + '}';
    }

}
