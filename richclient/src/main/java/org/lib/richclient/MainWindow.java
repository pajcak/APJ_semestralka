/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.lib.richclient.controller.AddBookAction;
import org.lib.richclient.controller.DeleteBooksAction;
import org.lib.richclient.controller.LibMenuBar;
import org.lib.richclient.controller.LibToolBar;
import static org.lib.utils.Messages.Library;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 *
 * @author danecek
 */
public class MainWindow extends Stage {

    public static MainWindow instance = new MainWindow();

    private BundleContext context;

    private final LibToolBar libToolBar = new LibToolBar();
    private final MenuBar libMenuBar = new LibMenuBar();

//    private MenuBar createMenubar() {
//        MenuBar mb = new MenuBar();
//        mb.getMenus().addAll(new BookMenu());
//        return mb;
//    }
//
//    private ToolBar createToolbar() {
//        ToolBar hbox = new ToolBar(AddBookAction.instance.createButton(),
//                DeleteBooksAction.instance.createButton());
//        hbox.setPadding(new Insets(2));//;Style("-fx-border-color: red;");
//        return hbox;
//    }
    public MainWindow() {
        setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                Framework f = (Framework) getContext().getBundle(0);
                try {
                    f.stop();
                    f.waitForStop(3000);
                } catch (BundleException | InterruptedException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.setTitle(Library.createMessage());
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(new BookPanel());
        VBox vb = new VBox(libMenuBar, libToolBar, splitPane);
        Scene s = new Scene(vb, 800, 600);
        this.setScene(s);
        this.centerOnScreen();
        this.show();
    }

    /**
     * @return the context
     */
    public BundleContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(BundleContext context) {
        this.context = context;
    }

    /**
     * @return the libToolBar
     */
    public LibToolBar getLibToolBar() {
        return libToolBar;
    }

    /**
     * @return the libMenuBar
     */
    public MenuBar getLibMenuBar() {
        return libMenuBar;
    }

}
