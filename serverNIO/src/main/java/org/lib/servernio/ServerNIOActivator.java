package org.lib.servernio;

import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ServerNIOActivator implements BundleActivator {

    static final Logger logger = Logger.getLogger(ServerNIOActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        Executors.newCachedThreadPool().execute(new NioServer(3333));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
