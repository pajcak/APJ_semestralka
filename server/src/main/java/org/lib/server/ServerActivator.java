package org.lib.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ServerActivator implements BundleActivator {

    static final Logger logger = Logger.getLogger(ServerActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        try {
            String portPrpty = context.getProperty("org.lib.server.port");
            int port = Integer.parseInt(portPrpty);
            new Thread(new ServerThread(port)).start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "property org.lib.server.port must be set");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
