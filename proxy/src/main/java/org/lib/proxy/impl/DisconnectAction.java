/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import javafx.event.ActionEvent;
import org.lib.connection.Connection;
import org.lib.richclient.controller.LibraryAction;

public class DisconnectAction extends LibraryAction {

    public static final DisconnectAction instance = new DisconnectAction();

    public DisconnectAction() {
        super("Disconnect");
    }

    @Override
    public void handle(ActionEvent t) {
        Connection.getInstance().disconnect();
    }

    @Override
    protected boolean feasible() {
        return Connection.getInstance().isConnected();
    }

}
