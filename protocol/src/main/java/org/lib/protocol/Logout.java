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
public class Logout extends AbstractCommand<Object> {

    @Override
    public Object execute(LibraryFacadeInterface f) throws LibraryException {
        return OK;
    }

    @Override
    public String toString() {
        return "Logout{" + '}';
    }

}
