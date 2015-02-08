package org.lib.connection;

import java.util.logging.Logger;
import javafx.application.Platform;
import org.lib.connection.impl.ConnectionImpl;  
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class ConnectionActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    public static BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        ServiceTracker<Connection, Connection> st = new ServiceTracker(context, Connection.class.getName(), null);
        st.open();
        Connection.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                ConnectionImpl.getInstance().disconnect();
            }
        });
    }
}
