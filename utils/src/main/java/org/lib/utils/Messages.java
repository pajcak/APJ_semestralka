/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public enum Messages {

    Library;

    public String createMessage(Object... pars) {
        ResourceBundle rs = ResourceBundle.getBundle("org.lib.utils.messages");
        try {
            return MessageFormat.format(rs.getString(name()), pars);
        } catch (RuntimeException ex) {
            Logger.getLogger(Messages.class.getName()).info("missing " + name());
            return name();
        }
    }

}
