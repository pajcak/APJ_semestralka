/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author danecek
 */
public class LibTextField extends TextField {

    public LibTextField(Validator validator) {
        this("", validator);
    }

    public LibTextField(String text, Validator validator) {
        super(text);
        this.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                validator.validate();
            }
        });
    }

}
