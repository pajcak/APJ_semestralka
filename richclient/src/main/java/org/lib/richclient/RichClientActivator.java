package org.lib.richclient;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.lib.richclient.controller.ActionState;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RichClientActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    @Override
    public void start(final BundleContext context) throws Exception {
        logger.info("");
        new JFXPanel();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                MainWindow.instance.setContext(context);
                ActionState.instance.fire();
            }
        });

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
