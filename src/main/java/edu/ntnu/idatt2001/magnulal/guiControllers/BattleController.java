package edu.ntnu.idatt2001.magnulal.guiControllers;

import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.model.simulator.Battle;
import edu.ntnu.idatt2001.magnulal.utils.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Battle controller to control the battleScreen scene
 * @author magnulal
 * @version 1.0
 * @since 0.3
 * TODO. comment all
 */
public class BattleController {
    private boolean hasSkipBeenPressed = false;
    private Timeline simulationTimeline;
    private int tenthSeconds = 0; //TODO: undo
    private Battle activeBattle;

    private static Image commanderImg;
    private static Image cavalryImg;
    private static Image infantryImg;
    private static Image rangedImg;
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
    @FXML
    private Button btnSimulate;
    @FXML
    private ScrollPane battleFeed;
    @FXML
    private Label terrainInfo;

    //TODO. ex handling on all button in GUI

    /**
     *
     */
    @FXML
    public void initialize() {
        visualArmy1.setStyle("-fx-background-color:#F5F5F5;");
        visualArmy2.setStyle("-fx-background-color:#F5F5F5;");
        btnSkipToResults.setDisable(true);

        try {
            commanderImg = new Image(new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/commanderBlue.png"));
            cavalryImg = new Image(new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/cavalryRed.png"));
            infantryImg = new Image(new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/infantryPurple.png"));
            rangedImg = new Image(new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/rangedYellow.png"));
        } catch (FileNotFoundException f){
            exMsg.setText(f.getMessage());
        }

        HBox hb = new HBox();
        Army army1 = ActiveArmies.getActiveArmy1();
        Army army2 = ActiveArmies.getActiveArmy2();
        nameArmy1.setText(army1.getName());
        nameArmy2.setText(army2.getName());
        numUnitsArmy1.setText(String.valueOf(army1.getAllUnits().size()));
        numUnitsArmy2.setText(String.valueOf(army2.getAllUnits().size()));
        VBox.setVgrow(visualArmy1, Priority.ALWAYS);

        updateVisualArmy1();
        updateVisualArmy2();
    }

    private void updateVisualArmy1(){
        HBox hb = new HBox();
        numUnitsArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getAllUnits().size()));
        VBox vb1 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getCommanderUnits().size(); i++) {
            vb1.getChildren().add(new ImageView(commanderImg));
        }
        VBox vb2 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getCavalryUnits().size(); i++) {
            vb2.getChildren().add(new ImageView(cavalryImg));
        }
        VBox vb3 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getInfantryUnits().size(); i++) {
            vb3.getChildren().add(new ImageView(infantryImg));
        }
        VBox vb4 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy1().getRangedUnits().size(); i++) {
            vb4.getChildren().add(new ImageView(rangedImg));
        }
        hb.getChildren().add(vb1);
        hb.getChildren().add(vb2);
        hb.getChildren().add(vb3);
        hb.getChildren().add(vb4);

        visualArmy1.setContent(hb);
    }


    /**
     *
     */
    private void updateVisualArmy2(){
        numUnitsArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getAllUnits().size()));

        HBox hb2 = new HBox();
        VBox vb1 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getCommanderUnits().size(); i++) {
            vb1.getChildren().add(new ImageView(commanderImg));
        }
        VBox vb2 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getCavalryUnits().size(); i++) {

            vb2.getChildren().add(new ImageView(cavalryImg));
        }
        VBox vb3 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getInfantryUnits().size(); i++) {
            vb3.getChildren().add(new ImageView(infantryImg));
        }
        VBox vb4 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getRangedUnits().size(); i++) {
            vb4.getChildren().add(new ImageView(rangedImg));
        }
        hb2.getChildren().add(vb1);
        hb2.getChildren().add(vb2);
        hb2.getChildren().add(vb3);
        hb2.getChildren().add(vb4);

        visualArmy2.setContent(hb2);
    }

    /**
     * Button click event to that switches the viewed scene to main being the home screen
     * If the simulation is ongoing, it is stopped.
     */
    @FXML
    public void returnHome() {
        if(simulationTimeline != null) simulationTimeline.stop();
        try {
            SceneManager.switchView("main");
        } catch (IOException e) {
            exMsg.setText(e.getMessage());
        }
    }

    /**
     * Button click event to inform the BattleController that the skip button has been pressed by the user.
     */
    @FXML
    public void skipToResults() {
        hasSkipBeenPressed = true;
    }

    @FXML
    public void simulateStart() {
        terrainInfo.setText("Terrain: " + ActiveTerrain.INSTANCE.getActiveTerrain().name());
        btnSimulate.setDisable(true);
        btnSkipToResults.setDisable(false);
        battleFeed.setVvalue(1.0);
        activeBattle = new Battle(ActiveArmies.getActiveArmy1(), ActiveArmies.getActiveArmy2(), ActiveTerrain.INSTANCE.getActiveTerrain());
        VBox vb = new VBox();
        battleFeed.setContent(vb);
        simulationTimeline = new Timeline(new KeyFrame(Duration.seconds(0.175), event -> { //TODO: improve to call less times -- use less memry
            if(!hasSkipBeenPressed) {
                //tenthSeconds++; TODO implement
                ArrayList<Object> battleLogInfo = activeBattle.simulateTurnForGUI();
                //retrieve the last element, which is always the active battle
                activeBattle = (Battle)battleLogInfo.get(battleLogInfo.size()-1);
                if(battleLogInfo.size() == 2){
                    vb.getChildren().add(new Label(String.valueOf(battleLogInfo.get(0))));
                }else {
                    vb.getChildren().add(new Label(String.valueOf(battleLogInfo.get(2))));
                }
                updateVisualArmy1();
                updateVisualArmy2();

                ActiveArmies.setActiveArmy1(activeBattle.getArmyOne());
                ActiveArmies.setActiveArmy2(activeBattle.getArmyTwo());
                if (!ActiveArmies.getActiveArmy1().hasUnits() || !ActiveArmies.getActiveArmy2().hasUnits()) {
                    simulationTimeline.stop();

                    if (ActiveArmies.getActiveArmy1().hasUnits()){
                        vb.getChildren().add(new Label(ActiveArmies.getActiveArmy1().getName() + " was the victorious army with" + ActiveArmies.getActiveArmy1().getAllUnits().size() + " units left. \n This is the victorious army: \n" + ActiveArmies.getActiveArmy1().toString()));
                        battleFeed.setVvalue(1.0);
                    }
                    //TODO: change to one call
                    if (ActiveArmies.getActiveArmy2().hasUnits()){
                        vb.getChildren().add(new Label(ActiveArmies.getActiveArmy2().getName() + " was the victorious army with" + ActiveArmies.getActiveArmy2().getAllUnits().size() + " units left. \n This is the victorious army: \n" + ActiveArmies.getActiveArmy2().toString()));
                        battleFeed.setVvalue(1.0);
                    }
                }
            }else{
                Army victoriousArmy = activeBattle.simulate();
                ActiveArmies.setActiveArmy1(activeBattle.getArmyOne());
                ActiveArmies.setActiveArmy2(activeBattle.getArmyTwo());
                battleFeed.setContent(new Label("Victorious army: \n" + victoriousArmy.toString()));
                updateVisualArmy1();
                updateVisualArmy2();
                simulationTimeline.stop();
            }
            battleFeed.setVvalue(1.0);

        }));
        simulationTimeline.setCycleCount(Animation.INDEFINITE);
        simulationTimeline.play();
        battleFeed.setVvalue(1.0);
    }
}
