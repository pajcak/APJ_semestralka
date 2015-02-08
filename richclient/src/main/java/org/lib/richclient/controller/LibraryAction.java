/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import java.util.ArrayList;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 *
 * @author danecek
 */
public abstract class LibraryAction implements EventHandler<ActionEvent> {

    String name;
    Collection<Disable> items = new ArrayList<>();

    public LibraryAction(String name) {
        this.name = name;
        ActionState.instance.registerAction(this);
    }

    public MenuItem createMenuItem() {
        LibMenuItem mi = new LibMenuItem(name);
        items.add(mi);
        mi.setOnAction(this);
        return mi;
    }

    public Button createButton() {
        LibButton lb = new LibButton(name);
        items.add(lb);
        lb.setOnAction(this);
        return lb;

    }

    public void disable(boolean dis) {
        for (Disable mi : items) {
            mi.setDisable(dis);
        }
    }

    protected abstract boolean feasible();

    void checkDisable() {
        disable(!feasible());
    }

}
