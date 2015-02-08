/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import javafx.scene.control.MenuBar;
import org.lib.richclient.BookMenu;

/**
 *
 * @author danecek
 */
public class LibMenuBar extends MenuBar {

    public LibMenuBar() {
        getMenus().addAll(new BookMenu());
    }

}
