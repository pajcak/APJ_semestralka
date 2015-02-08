/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import javafx.event.ActionEvent;
import org.lib.richclient.AddBookDialog;

public class AddBookAction extends LibraryAction {

    public static final AddBookAction instance = new AddBookAction();

    public AddBookAction() {
        super("Add Book"); // todo
    }

    @Override
    public void handle(ActionEvent t) {
        new AddBookDialog().show();
    }

    @Override
    protected boolean feasible() {
        return true;
    }

}
