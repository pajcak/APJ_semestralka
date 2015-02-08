/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import javafx.geometry.Insets;
import javafx.scene.control.ToolBar;

/**
 *
 * @author danecek
 */
public class LibToolBar extends ToolBar {

    public LibToolBar() {
        super(AddBookAction.instance.createButton(),
                DeleteBooksAction.instance.createButton());
    }

}
