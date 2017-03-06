package com.gluonapplication.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ChapterSelectionPresenter {

    public static String chapterSelected;

    @FXML
    private ListView chapterList;
    public Button logout;

    public void initialize() throws SQLException {

        AppBar appBar = MobileApplication.getInstance().getAppBar();
        appBar.setTitleText("Chapter Selection");
        ObservableList<String> chap = FXCollections.observableArrayList();

        try {
            /*
            chapterList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(final ListView<String> list) {
                    return new ListCell<String>() {
                        {
                            Text text = new Text();
                            text.wrappingWidthProperty().bind(list.widthProperty().subtract(0));
                            text.textProperty().bind(itemProperty());
                            //text.setText("");
                            //setPrefWidth(100);
                            setGraphic(text);
                        }
                    };
                }
            });
*/
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://mcdb.cxnrl6c0pjuj.us-east-1.rds.amazonaws.com:3306/Questions", "students", "students");
            System.out.println("Database connected");
            // Create a Statement
            PreparedStatement pstmt = connection.prepareStatement("select chapterdes, chapter from Questions");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String chapterTitle = rs.getString("chapterdes");
                String chapterNum = rs.getString("chapter");
                if (!chap.contains(chapterNum + ". " + chapterTitle)) {
                    // Text a = new Text();
                    //a.setText(chapterNum + ". " + chapterTitle);
                    chap.add(chapterNum + ". " + chapterTitle);
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        chapterList.setItems(chap);

    }

    @FXML
    void touch(Event event) throws IOException {
        chapterSelected = Integer.toString(chapterList.getSelectionModel().getSelectedIndex() + 1);
        Parent pane = FXMLLoader.load(getClass().getResource("Sections.fxml"));
        chapterList.getScene().setRoot(pane);
    }

    @FXML
    void log(Event event) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource("start_page.fxml"));
        logout.getScene().setRoot(pane);
    }

}
