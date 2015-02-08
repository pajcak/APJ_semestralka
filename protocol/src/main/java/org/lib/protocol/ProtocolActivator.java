package org.lib.protocol;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProtocolActivator implements BundleActivator {

    private static BundleContext context;

    static final Logger logger = Logger.getGlobal();

    /**
     * @return the context
     */
    public static BundleContext getContext() {
        return context;
    }

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        ProtocolActivator.context = context;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
