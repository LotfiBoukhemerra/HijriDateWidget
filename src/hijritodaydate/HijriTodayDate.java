/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hijritodaydate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Lotfi Boukhemerra
 */
public class HijriTodayDate extends Application {

    static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36 OPR/69.0.3686.95";

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Label hijridate = new Label();
        hijridate.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        hijridate.setLayoutX(55.0);
        hijridate.setLayoutY(20.0);
        hijridate.setTextFill(Color.RED);
        hijridate.setText(getHijriTodayDate());
        root.getChildren().add(hijridate);
        Scene scene = new Scene(root, 650.0, 80.0);

        stage.setScene(scene);
        stage.sizeToScene();
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(false);
        stage.setResizable(false);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.show();

    }//end start

    public String getHijriTodayDate() {
        String date = "connection problem";
        try {
            //whois-title
            Connection.Response fitch;
            fitch = Jsoup.connect("https://hijricalendars.com/").method(Connection.Method.GET).userAgent(USER_AGENT).execute();
            Document doc = fitch.parse(); // this is the document that contains response html
            Elements element = doc.getElementsByClass("full_today");
            //System.out.println(element.text()); 
            date = element.text();
            
        } catch (IOException ex) {
            Logger.getLogger(HijriTodayDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }//end main

}//end class
