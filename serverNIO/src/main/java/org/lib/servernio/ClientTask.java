/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.servernio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.utils.LibraryException;
import org.lib.utils.Marshaller;

/**
 *
 * @author danecek
 */
public class ClientTask implements Runnable {

    private static final Logger LOG = Logger.getLogger(ClientTask.class.getName());
    static final int RESULT_BUFF_LEN = 8196;

    private final byte[] commBytes;
    private final SocketChannel socketChannel;

    public ClientTask(byte[] commBytes, SocketChannel socketChannel) {
        this.commBytes = commBytes;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            Object result;
            AbstractCommand comm = null;
            try {
                comm = (AbstractCommand) Marshaller.bytes2Object(commBytes);
                LOG.info(comm.toString());
                result = comm.execute(LibraryFacade.getInstance());
            } catch (LibraryException ex) {
                Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
                result = ex;
            }
            //         LOG.info(result.toString());
            byte[] resultBytes = Marshaller.obj2Bytes(result);
            ByteBuffer writeBuff = ByteBuffer.allocate(resultBytes.length + 2);
            writeBuff.putShort((short) resultBytes.length);
            writeBuff.put(resultBytes);
            writeBuff.flip();
            //       LOG.info(writeBuff.toString());
            socketChannel.write(writeBuff);
            if (comm instanceof Logout) {
                socketChannel.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
