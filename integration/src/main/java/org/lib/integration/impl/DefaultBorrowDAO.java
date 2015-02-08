/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import org.lib.integration.BorrowDAO;
import org.lib.model.BookId;
import org.lib.model.LibReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class DefaultBorrowDAO implements BorrowDAO {

    Map<LibReaderId, Collection<BookId>> borrows;

    @Override
    public void createBorrow(LibReaderId readerId, BookId bookId) throws LibraryException {
        Collection<BookId> bs = borrows.get(readerId);
        if (bs == null) {
            bs = new HashSet<>();
            borrows.put(readerId, bs);
        }
        bs.add(bookId);
    }

    @Override
    public void deleteBorrow(LibReaderId readerId, BookId bookId) throws LibraryException {
        Collection<BookId> bs = borrows.get(readerId);
        if (bs != null) {
            bs.remove(bookId);
        }

    }

}
