package org.lib.proxy;

import org.lib.proxy.impl.DisconnectAction;
import org.lib.proxy.impl.ConnectionMenu;
import org.lib.proxy.impl.ConnectAction;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.lib.business.LibraryFacade;
import org.lib.proxy.impl.LibraryFacadeProxy;
import org.lib.richclient.MainWindow;
import org.lib.richclient.controller.ActionState;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProxyActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        context.registerService(LibraryFacade.class.getName(), new LibraryFacadeProxy(), null);
                Platform.runLater(new Runnable() {

            @Override
            public void run() {
                MainWindow.instance.getLibToolBar().getItems().addAll(ConnectAction.instance.createButton());
                MainWindow.instance.getLibToolBar().getItems().addAll(DisconnectAction.instance.createButton());
                MainWindow.instance.getLibMenuBar().getMenus().addAll(new ConnectionMenu());
                ActionState.instance.fire();
            }
        });
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
