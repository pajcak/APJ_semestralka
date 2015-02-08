/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import javafx.scene.control.Menu;
import org.lib.richclient.controller.AddBookAction;
import org.lib.richclient.controller.DeleteBooksAction;

public class BookMenu extends Menu {

    public BookMenu() {
        super("Books");  // todo lok
        getItems().addAll(AddBookAction.instance.createMenuItem(),
                          DeleteBooksAction.instance.createMenuItem()
                         // ConnectAction.instance.createMenuItem()
        );
    }

}
