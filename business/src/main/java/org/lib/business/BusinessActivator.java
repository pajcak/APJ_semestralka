package org.lib.business;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class BusinessActivator implements BundleActivator {

    Logger logger = Logger.getGlobal();

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        ServiceTracker<LibraryFacade, LibraryFacade> st = new ServiceTracker(context, LibraryFacade.class.getName(), null);
        st.open();
        LibraryFacade.setSt(st);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
