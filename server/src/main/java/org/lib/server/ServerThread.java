/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.business.LibraryFacadeInterface;

public class ServerThread implements Runnable {

    static Lock lock = new ReentrantLock();

    private static LibraryFacadeInterface syncWrapper() {

        return (LibraryFacadeInterface) Proxy.newProxyInstance(ServerThread.class.getClassLoader(),
                new Class<?>[]{LibraryFacadeInterface.class},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object o, Method method, Object[] os) throws Throwable {
                        lock.tryLock();
                        try {
                            return method.invoke(LibraryFacade.getInstance(), os);
                        } finally {
                            lock.unlock();
                        }
                    }
                });
    }

    static LibraryFacadeInterface syncFacade = syncWrapper();

    static final Logger logger = Logger.getLogger(ServerThread.class.getName());

    ServerSocket ss;
    int port;

    public ServerThread(int port) {
        this.port = port;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public void run() {
        logger.info("Server is waiting on port: " + port);
        try {
            for (;;) {
                logger.info("Waiting for client");
                Socket s = ss.accept();
                threadPool.execute(new ClientTask(s, syncFacade));
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
