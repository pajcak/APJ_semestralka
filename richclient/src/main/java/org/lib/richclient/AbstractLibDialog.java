/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lib.richclient.controller.Validator;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibDialog extends Stage implements Validator {

    Button cancelButton = new Button("Cancel");
    Button okButton = new Button("OK");
    protected Text errorMessage = new Text();

    public AbstractLibDialog(String title) {
        errorMessage.setStyle("-fx-fill: red");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                hide();
            }
        });
        setTitle(title);
        setAlwaysOnTop(true);
        setResizable(false);
        centerOnScreen();
        initOwner(MainWindow.instance);
        initModality(Modality.APPLICATION_MODAL);
        VBox vbox = new VBox(createInterior(), createButtonbox(), errorMessage);
        vbox.setPadding(new Insets(10));
        Scene s = new Scene(vbox);
        setScene(s);
        show();
    }

    protected Button getOkButon() {
        return okButton;
    }

    protected abstract Node createInterior();

    protected HBox createButtonbox() {
        HBox buttons = new HBox(getOkButon(), cancelButton);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        return buttons;
    }

}
