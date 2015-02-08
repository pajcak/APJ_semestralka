/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import org.lib.integration.AbstractDAOFactory;
import org.lib.integration.BookDAO;
import org.lib.integration.BorrowDAO;

/**
 *
 * @author danecek
 */
public class DefaultDAOFactory extends AbstractDAOFactory {

    @Override
    public BookDAO getBooksDAO() {
        return new DefaultBookDAO();
    }

    @Override
    public BorrowDAO getBorrowDAO() {
        return new DefaultBorrowDAO();
    }
    
}
