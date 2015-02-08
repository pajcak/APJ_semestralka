/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.io.IOException;
import org.lib.connection.impl.ConnectionImpl;
import org.lib.protocol.AbstractCommand;
import org.lib.utils.LibraryException;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class Connection {

    private static Connection instance;

    private static ServiceTracker<Connection, Connection> st;

    /**
     * @return the instance
     */
    public static Connection getInstance() {
        if (instance == null) {
            instance = st.getService();
            if (instance == null) {
                instance = new ConnectionImpl();
            }
        }
        return instance;
    }

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker<Connection, Connection> aSt) {
        st = aSt;
    }

    public abstract void connect(String host, int port) throws IOException;

    public abstract <T> T send(AbstractCommand comm) throws LibraryException;

    public abstract boolean isConnected();

    public abstract void disconnect();

}
