package org.lib.derbydb;

import java.util.logging.Logger;
import org.lib.derbydb.impl.DerbyDAOFactory;
import org.lib.integration.AbstractDAOFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DerbyDBActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    private DerbyDAOFactory derbyDAOFactory;

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        derbyDAOFactory = new DerbyDAOFactory();
        context.registerService(AbstractDAOFactory.class.getName(), derbyDAOFactory, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
        derbyDAOFactory.closeConnection();
    }

}
