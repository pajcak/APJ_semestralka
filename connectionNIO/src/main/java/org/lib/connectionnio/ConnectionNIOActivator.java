package org.lib.connectionnio;

import org.lib.connectionnio.impl.ConnectionNIO;
import java.util.logging.Logger;
import org.lib.connection.Connection;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ConnectionNIOActivator implements BundleActivator {

    static final Logger logger = Logger.getLogger(ConnectionNIOActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        context.registerService(Connection.class.getName(), ConnectionNIO.getInstance(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
