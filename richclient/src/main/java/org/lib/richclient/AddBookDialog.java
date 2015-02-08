/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.lib.business.LibraryFacade;
import org.lib.richclient.controller.ActionState;
import org.lib.richclient.controller.LibTextField;
import org.lib.richclient.controller.Validator;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class AddBookDialog extends AbstractLibDialog implements Validator {

    LibTextField title;// = new LibTextField();
    LibTextField author;// = new LibTextField();

    @Override
    public boolean validate() {
        if (title.getText() == null || title.getText().isEmpty()) {
            errorMessage.setText("empty title");
            getOkButon().setDisable(true);
            return false;
        }
        if (author.getText() == null || author.getText().isEmpty()) {
            errorMessage.setText("empty author");
            getOkButon().setDisable(true);
            return false;
        }
        errorMessage.setText("");
        getOkButon().setDisable(false);
        return true;
    }

    @Override
    protected Node createInterior() {
        title = new LibTextField(this);
        author = new LibTextField(this);
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(new Label("Title:"), 0, 0);
        gp.add(new Label("Author:"), 1, 0);
        gp.add(title, 0, 1);
        gp.add(author, 1, 1);
        return gp;
    }

    public AddBookDialog() {
        super("Add Book"); // todo
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                hide();
            }
        });
        validate();
        getOkButon().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (validate()) {
                    try {
                        LibraryFacade.getInstance().createBook(title.getText(), author.getText());
                        DataState.instance.invalidate();
                        ActionState.instance.fire();
                        hide();
                    } catch (LibraryException ex) {
                        new MessageDialog(ex.getMessage());
                    }
                }
            }
        });
    }

}
