/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.richclient;

import java.util.Collection;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.richclient.controller.ActionState;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class BookPanel extends TitledPane implements InvalidationListener {
    
    public static BookPanel instance;
    
    private Label createTitle() {
        Label title = new Label("Books");
        title.setStyle("-fx-font: 16px \"Serif\";\n"
                + "    -fx-padding: 10;\n"
                + "    -fx-background-color: #CCFF99;");
        return title;
    }
    
    ObservableList<Book> data = FXCollections.observableArrayList();
    
    private TableView<Book> createTable() {
        TableView<Book> tab = new TableView<>();
        tab.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Book, BookId> idCol = new TableColumn<>("Id");  // todo lok
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, BookId> titleCol = new TableColumn<>("Title");  // todo lok
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, BookId> authorCol = new TableColumn<>("Author");  // todo lok
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        tab.getColumns().addAll(idCol, titleCol, authorCol);
        tab.setItems(data);
        tab.getSelectionModel().getSelectedCells().addListener(new InvalidationListener() {
            
            @Override
            public void invalidated(Observable observable) {
                ActionState.instance.fire();
            }
        });
        return tab;
    }
    
    TableView<Book> table;
    
    public BookPanel() {
        instance = this;
        setText("Books"); //todo
        setContent(table = createTable());
        DataState.instance.addListener(this);
        getChildren().addAll(createTitle(), table);
        invalidated(null);
    }
    
    public ObservableList<Book> getSelected() {
        ObservableList<Book> selected = table.getSelectionModel().getSelectedItems();
        return selected;
    }
    
    @Override
    public void invalidated(Observable o) {
        
        data.clear();
        try {
            Collection<Book> books = LibraryFacade.getInstance().getAllBooks();
            data.addAll(books);
        } catch (LibraryException ex) {
            new MessageDialog(ex.getMessage());
        }
    }
    
}
