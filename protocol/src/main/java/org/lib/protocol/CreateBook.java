/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import org.lib.business.LibraryFacadeInterface;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class CreateBook extends AbstractCommand<String> {

    private final String title;
    private final String author;

    public CreateBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String execute(LibraryFacadeInterface f) throws LibraryException {
        f.createBook(title, author);
        return OK;
    }

    @Override
    public String toString() {
        return "CreateBook{" + title + ", " + author + '}';
    }

}
