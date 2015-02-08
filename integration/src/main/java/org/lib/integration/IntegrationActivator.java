package org.lib.integration;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class IntegrationActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        ServiceTracker<AbstractDAOFactory, AbstractDAOFactory> st
                = new ServiceTracker<>(context, AbstractDAOFactory.class.getName(), null);
        st.open();
        AbstractDAOFactory.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
