/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connectionnio.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.connection.Connection;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.richclient.controller.ActionState;
import org.lib.utils.LibraryException;
import org.lib.utils.Marshaller;
import static org.lib.utils.Marshaller.bytes2Object;

/**
 *
 * @author danecek
 */
public class ConnectionNIO extends Connection {

    static final Logger LOG = Logger.getLogger(ConnectionNIO.class.getName());
    private static final ConnectionNIO instance = new ConnectionNIO();

    /**
     * @return the instance
     */
    public static ConnectionNIO getInstance() {
        return instance;
    }

    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    private ConnectionNIO() {
    }

    @Override
    public void connect(String host, int port) throws IOException {
        if (isConnected()) {
            return;
        }
        socket = new Socket(host, port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        socket.setSoTimeout(2000);
        ActionState.instance.fire();
    }

    private static Object readObject(DataInputStream dis) throws IOException {
        int len = dis.readShort();
        byte[] result = new byte[len];
        dis.read(result);
        return bytes2Object(result);
    }

    @Override
    public <T> T send(AbstractCommand comm) throws LibraryException {

        // logger.info(socket.toString());
        if (socket == null) {
            throw new LibraryException("Not connected");
        }
        LOG.log(Level.INFO, "comand: {0}", comm.toString());
        try {
            byte[] commBytes = Marshaller.obj2Bytes(comm);
            dos.writeShort(commBytes.length);
            dos.write(commBytes);
            dos.flush();
            Object result = readObject(dis);
            LOG.log(Level.INFO, "result: {0}", result.getClass());
            if (result instanceof LibraryException) {
                throw (LibraryException) result;
            }
            return (T) result;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new LibraryException(ex);
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
            Logger.getLogger(ConnectionNIO.class
                    .getName()).info(ex.toString());
        }
        try (Socket socket = this.socket;
                InputStream is = this.dis;
                OutputStream os = this.dos) {

        } catch (IOException ex) {
        }
        socket = null;
        ActionState.instance.fire();
    }

}
