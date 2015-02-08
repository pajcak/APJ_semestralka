/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.connection.Connection;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.richclient.controller.ActionState;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ConnectionImpl extends Connection {

    ObjectInputStream ois;
    ObjectOutputStream oos;
    private Socket socket;

    @Override
    public void connect(String host, int port) throws IOException {
        if (isConnected()) {
            return;
        }
        socket = new Socket(host, port);
        socket.setSoTimeout(2000);
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        ActionState.instance.fire();
    }

    @Override
    public <T> T send(AbstractCommand comm) throws LibraryException {

        if (socket == null) {
            throw new LibraryException("Not connected");
        }
        try {
            oos.writeObject(comm);
            oos.flush();
            Object result = ois.readObject();
            if (result instanceof LibraryException) {
                throw (LibraryException) result;
            }
            return (T) result;
        } catch (IOException ex) {
            throw new LibraryException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isConnected() {
        return socket != null;
    }

    @Override
    public void disconnect() {
        if (!isConnected()) {
            return;
        }
        try {
            send(new Logout());
        } catch (LibraryException ex) {
            Logger.getLogger(ConnectionImpl.class
                    .getName()).info(ex.toString());
        }
        try (Socket socket = this.socket;
                ObjectInputStream ois = this.ois;
                ObjectOutputStream oos = this.oos) {

        } catch (IOException ex) {
        }
        socket = null;
        ActionState.instance.fire();

    }

}
