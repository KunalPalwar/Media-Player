/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SpringLayout;

/**
 *
 * @author 7
 */
public class FXMLDocumentController implements Initializable {
    
    private String filepath;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider slider;
    @FXML
    private Slider seekSlider;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       FileChooser filechooser =new FileChooser();
       FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select a mkv file","*.mp4");
       filechooser.getExtensionFilters().add(filter);
       File file= filechooser.showOpenDialog(null);
       filepath = file.toURI().toString();
       
       Media media = new Media(filepath);
       mediaPlayer =new MediaPlayer(media);
       mediaView.setMediaPlayer(mediaPlayer);
       DoubleProperty width = mediaView.fitWidthProperty();
       DoubleProperty height = mediaView.fitHeightProperty();
       width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
       height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
       
       slider.setValue(mediaPlayer.getVolume()*100);
       slider.valueProperty().addListener(new InvalidationListener() {
           @Override
           public void invalidated(Observable arg0) {
               mediaPlayer.setVolume(slider.getValue()/100);
           }
       });
       
       mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
           @Override
           public void changed(ObservableValue<? extends Duration> arg0, Duration arg1, Duration arg2) {
              seekSlider.setValue(arg2.toSeconds());
           }
       });
       seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent arg0) {
              mediaPlayer.seek(Duration.seconds(seekSlider.getValue())); 
           }
       });
       
       mediaPlayer.play();
    }
    @FXML
    private void playButton(ActionEvent event){
       mediaPlayer.play();
       mediaPlayer.setRate(1);
       
    }
    @FXML
    private void pauseButton(ActionEvent event){
        mediaPlayer.pause();
        
       
                
    }
    
    
     @FXML
      private void fastButton(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }
      @FXML
       private void fasterButton(ActionEvent event){
        mediaPlayer.setRate(1.75);
    }
       @FXML
        private void slowButton(ActionEvent event){
        mediaPlayer.setRate(0.75);
    }
        @FXML
         private void slowerButton(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
