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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author danecek
 */
public class MessageDialog extends Stage {

    Button okButton = new Button("OK");
    Text errorMessage = new Text();

    public MessageDialog(String title) {
        errorMessage.setWrappingWidth(400);
        errorMessage.setText(title);
        errorMessage.setStyle("-fx-fill: red");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                hide();
            }
        });
        setTitle("Message");
        setAlwaysOnTop(true);
        setResizable(false);
        centerOnScreen();
        initOwner(MainWindow.instance);
        initModality(Modality.APPLICATION_MODAL);
        errorMessage.setTextAlignment(TextAlignment.CENTER);
        VBox v = new VBox(new StackPane(errorMessage), createButtonbox());
        v.setPadding(new Insets(10));
       // v.set
        Scene s = new Scene(v);
        setScene(s);
        show();
    }

    private HBox createButtonbox() {
        HBox buttons = new HBox(okButton);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        return buttons;
    }

}
