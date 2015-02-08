/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import org.lib.integration.impl.DefaultDAOFactory;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class AbstractDAOFactory {

    private static AbstractDAOFactory instance;
    private static ServiceTracker<AbstractDAOFactory, AbstractDAOFactory> st;

    public static AbstractDAOFactory getInstance() {
        if (instance == null) {
            instance = st.getService();
            if (instance == null) {
                instance = new DefaultDAOFactory();
            }
        }
        return instance;
    }

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker aSt) {
        st = aSt;
    }

    public abstract BookDAO getBooksDAO();

    public abstract BorrowDAO getBorrowDAO();

}
