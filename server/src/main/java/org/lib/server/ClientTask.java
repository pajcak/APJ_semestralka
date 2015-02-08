/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacadeInterface;
import org.lib.protocol.AbstractCommand;
import org.lib.protocol.Logout;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ClientTask implements Runnable {

    Socket s;
    ObjectInputStream inp;
    ObjectOutputStream out;
    String client;

//    static Class[] classes = {LibReaderId.class, BookId.class, Book.class,
//        Address.class, Borrow.class, LibReader.class};

    static final Logger logger = Logger.getLogger(ClientTask.class.getName());
    LibraryFacadeInterface facade;

    public ClientTask(Socket s, LibraryFacadeInterface facade) {
        this.facade = facade;
        client = s.getInetAddress() + ":" + s.getPort();
        try {
            this.s = s;
            out = new ObjectOutputStream(s.getOutputStream());
            inp = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "New client: {0}", client);
        try (ObjectInputStream linp = inp;
                ObjectOutputStream lout = out;
                Socket ls = s) {
            for (;;) {
                try {
                    AbstractCommand com = (AbstractCommand) linp.readObject();
                    logger.log(Level.INFO, "{0}: {1}", new Object[]{client, com});
                    if (com instanceof Logout) {
                        logger.info("Client loggedout");
                        break;
                    }
                    Object result;
                    try {
                        result = com.execute(facade);//LibraryFacade.getInstance());
                    } catch (LibraryException ex) {
                        result = ex;
                    }
                    lout.writeObject(result);
                    lout.flush();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(1);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
