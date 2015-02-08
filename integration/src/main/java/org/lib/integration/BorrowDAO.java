/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import org.lib.model.BookId;
import org.lib.model.LibReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface BorrowDAO {

    void createBorrow(LibReaderId readerId, BookId bookId) throws LibraryException;

    void deleteBorrow(LibReaderId readerId, BookId bookId) throws LibraryException;

}
