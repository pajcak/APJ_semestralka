/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.io.Serializable;
import org.lib.business.LibraryFacadeInterface;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public abstract class AbstractCommand<T> implements Serializable {

    public static final String OK = "ok";

    public abstract T execute(LibraryFacadeInterface f) throws LibraryException;
}
