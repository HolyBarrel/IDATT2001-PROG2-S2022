package edu.ntnu.idatt2001.magnulal.guiControllers;

import edu.ntnu.idatt2001.magnulal.Main;
import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.model.units.Unit;
import edu.ntnu.idatt2001.magnulal.utils.ActiveArmies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.swing.text.Position;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class BattleController {
    private static boolean hasSkipBeenPressed = false;

    @FXML
    private Button btnSkipToResults;
    @FXML
    private Button btnReturnHome;
    @FXML
    private Label exMsg;
    @FXML
    private Label nameArmy2;
    @FXML
    private Label nameArmy1;
    @FXML
    private Label numUnitsArmy2;
    @FXML
    private ScrollPane visualArmy1;
    @FXML
    private Label numUnitsArmy1;
    @FXML
    private ScrollPane visualArmy2;
    @FXML
    private AnchorPane visualPaneArmy2;
    @FXML
    private AnchorPane visualPaneArmy1;

    private final HBox hb = new HBox();
    private final HBox hb2 = new HBox();

    @FXML
    public void initialize() throws FileNotFoundException {
        Army army1 = ActiveArmies.getActiveArmy1();
        Army army2 = ActiveArmies.getActiveArmy2();
        nameArmy1.setText(army1.getName());
        nameArmy2.setText(army2.getName());
        numUnitsArmy1.setText(String.valueOf(army1.getAllUnits().size()));
        numUnitsArmy2.setText(String.valueOf(army2.getAllUnits().size()));
        final ImageView[] picsArmy1 = new ImageView[5];
        //Image roses = new Image(getClass().getResourceAsStream("roses.jpg"));

        VBox.setVgrow(visualArmy1, Priority.ALWAYS);


        //for(Unit unit : army1.getAllUnits()){
         //   inputstream.read()
        //}

        VBox vb1 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getCommanderUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/commanderBlue.png");
            vb1.getChildren().add(new ImageView(new Image(inputstream)));
        }
        VBox vb2 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getCavalryUnits().size(); i++) {

            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/cavalryRed.png");
            vb2.getChildren().add(new ImageView(new Image(inputstream)));
        }
        VBox vb3 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getInfantryUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/infantryPurple.png");
            vb3.getChildren().add(new ImageView(new Image(inputstream)));
        }
        VBox vb4 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getRangedUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/rangedYellow.png");
            vb4.getChildren().add(new ImageView(new Image(inputstream)));
        }
        hb.getChildren().add(vb1);
        hb.getChildren().add(vb2);
        hb.getChildren().add(vb3);
        hb.getChildren().add(vb4);

        visualArmy1.setContent(hb);

        //TODO: define local variables
        //TODO: if more than 30
        vb1 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getCommanderUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/commanderBlue.png");
            vb1.getChildren().add(new ImageView(new Image(inputstream)));
        }
        vb2 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getCavalryUnits().size(); i++) {

            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/cavalryRed.png");
            vb2.getChildren().add(new ImageView(new Image(inputstream)));
        }
        vb3 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getInfantryUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/infantryPurple.png");
            vb3.getChildren().add(new ImageView(new Image(inputstream)));
        }
        vb4 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getRangedUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/rangedYellow.png");
            vb4.getChildren().add(new ImageView(new Image(inputstream)));
        }

        hb2.getChildren().add(vb1);
        hb2.getChildren().add(vb2);
        hb2.getChildren().add(vb3);
        hb2.getChildren().add(vb4);

        //ScrollPane sp = new ScrollPane();
        visualArmy2.setContent(hb2);

        //Passing FileInputStream object as a parameter
        //FileInputStream inputstream = new FileInputStream("edu.ntnu.idatt2001.magnulal/images/commanderBlue.png");
        //Image image = new Image(inputstream);
        //String path = String.format("/edu.ntnu.idatt2001.magnulal/images/%s.png", "commanderBlue");
        //Image image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/resources/edu.ntnu.idatt2001.magnulal/images/commanderBlue.png")));
        //visualArmy1.setContent(new ImageView(image));
//Loading image from URL
//Image image = new Image(new FileInputStream("url for the image));

    }
    @FXML
    public void returnHome(ActionEvent actionEvent) {
    }

    @FXML
    public void skipToResults(ActionEvent actionEvent) {
        hasSkipBeenPressed = true;
    }
}
