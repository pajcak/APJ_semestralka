package org.lib.model;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ModelActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
