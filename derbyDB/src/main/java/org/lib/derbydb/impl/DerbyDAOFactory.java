/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.AbstractDAOFactory;
import org.lib.integration.BookDAO;
import org.lib.integration.BorrowDAO;

public class DerbyDAOFactory extends AbstractDAOFactory {

    Connection dbConnection;
    BookDAO bookDAO;

    public DerbyDAOFactory() {
        try {
            dbConnection = createConnection();
            dbConnection.setAutoCommit(true);
            DatabaseMetaData m = dbConnection.getMetaData();
            ResultSet rs = m.getTables(null, null, "BOOKS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).
                        log(Level.INFO, "Table BOOKS generated");
                Statement s = dbConnection.createStatement();
                s.executeUpdate("CREATE TABLE BOOKS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "TITLE   VARCHAR(50),"
                        + "AUTHOR  VARCHAR(50),"
                        + "PRIMARY KEY (ID))");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection createConnection() {
        Connection dbCon = null;
        try {
            //  context.getBundle().loadClass("org.apache.derby.jdbc.EmbeddedDriver");
            // getClass().getClassLoader().loadClass("org.apache.derby.jdbc.EmbeddedDriver");
            // Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
            //    context.getBundle(null).getBundleContext().loadClass("org.apache.derby.jdbc.EmbeddedDriver");
            //   Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, context.getBundle().loadClass(null));
            // Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, Thread.currentThread().getContextClassLoader());
            new org.apache.derby.jdbc.EmbeddedDriver();
            String url = "jdbc:derby:" + System.getProperty("user.home") + "/libraryDB; create=true";
            dbCon = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbCon;
    }

    @Override
    public BookDAO getBooksDAO() {
        if (bookDAO == null) {
            bookDAO = new DerbyBookDAO(dbConnection);
        }
        return bookDAO;
    }

    @Override
    public BorrowDAO getBorrowDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
