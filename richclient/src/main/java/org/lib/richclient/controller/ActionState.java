/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient.controller;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author danecek
 */
public class ActionState {

    public final static ActionState instance = new ActionState();

    private final Collection<LibraryAction> actions = new ArrayList<>();

    private ActionState() {
    }

    public void registerAction(LibraryAction libAction) {
        actions.add(libAction);
    }

    public void fire() {
        for (LibraryAction libAction : actions) {
            libAction.checkDisable();
        }
    }

}
