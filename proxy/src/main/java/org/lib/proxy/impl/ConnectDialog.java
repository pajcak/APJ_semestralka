/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.lib.connection.Connection;
import org.lib.richclient.AbstractLibDialog;
import org.lib.richclient.DataState;
import org.lib.richclient.MessageDialog;
import org.lib.richclient.controller.LibTextField;
import org.lib.richclient.controller.Validator;

/**
 *
 * @author danecek
 */
public final class ConnectDialog extends AbstractLibDialog implements Validator {

    LibTextField host;
    LibTextField port;

    @Override
    public boolean validate() {
        boolean ok = true;
        String err = "";
        if (host.getText() == null || host.getText().isEmpty()) {
            err = "empty host";
            ok = false;
        } else {
            try {
                Integer.parseInt(port.getText());
            } catch (NumberFormatException ex) {
                err = "invalid port";
                ok = false;
            }

        }
        errorMessage.setText(err);
        getOkButon().setDisable(!ok);
        return ok;
    }

    @Override
    protected Node createInterior() {
        host = new LibTextField("localhost", this);
        port = new LibTextField("3333", this);
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(new Label("Host:"), 0, 0);
        gp.add(new Label("Port:"), 1, 0);
        gp.add(host, 0, 1);
        gp.add(port, 1, 1);
        return gp;
    }

    public ConnectDialog() {
        super("Connect"); // todo

        getOkButon().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (validate()) {
                    try {
                        Connection.getInstance().connect(host.getText(), Integer.parseInt(port.getText()));
                        DataState.instance.invalidate();
                    } catch (IOException ex) {
                        new MessageDialog(ex.toString());
                    }
                    hide();
                }
            }
        });
        validate();
    }

}
