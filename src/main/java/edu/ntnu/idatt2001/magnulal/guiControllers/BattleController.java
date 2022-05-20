package edu.ntnu.idatt2001.magnulal.guiControllers;

import edu.ntnu.idatt2001.magnulal.Main;
import edu.ntnu.idatt2001.magnulal.model.simulator.Army;
import edu.ntnu.idatt2001.magnulal.model.simulator.Battle;
import edu.ntnu.idatt2001.magnulal.model.units.Unit;
import edu.ntnu.idatt2001.magnulal.utils.ActiveArmies;
import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import edu.ntnu.idatt2001.magnulal.utils.SceneManager;
import edu.ntnu.idatt2001.magnulal.utils.TerrainType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BattleController {
    private boolean hasSkipBeenPressed = false;
    private Timeline simulationTimeline;
    private int tenthSeconds = 0;
    private Battle activeBattle;
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

    private void updateVisualArmy1() throws FileNotFoundException {
        HBox hb = new HBox();
        numUnitsArmy1.setText(String.valueOf(ActiveArmies.getActiveArmy1().getAllUnits().size()));
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
    }


    private void updateVisualArmy2() throws FileNotFoundException {
        numUnitsArmy2.setText(String.valueOf(ActiveArmies.getActiveArmy2().getAllUnits().size()));

        HBox hb2 = new HBox();
        VBox vb1 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getCommanderUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/commanderBlue.png");
            vb1.getChildren().add(new ImageView(new Image(inputstream)));
        }
        VBox vb2 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getCavalryUnits().size(); i++) {

            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/cavalryRed.png");
            vb2.getChildren().add(new ImageView(new Image(inputstream)));
        }
        VBox vb3 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getInfantryUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/infantryPurple.png");
            vb3.getChildren().add(new ImageView(new Image(inputstream)));
        }
        VBox vb4 = new VBox();
        for (int i = 0; i < ActiveArmies.getActiveArmy2().getRangedUnits().size(); i++) {
            FileInputStream inputstream = new FileInputStream("src/main/resources/edu.ntnu.idatt2001.magnulal/images/rangedYellow.png");
            vb4.getChildren().add(new ImageView(new Image(inputstream)));
        }
        hb2.getChildren().add(vb1);
        hb2.getChildren().add(vb2);
        hb2.getChildren().add(vb3);
        hb2.getChildren().add(vb4);

        visualArmy2.setContent(hb2);
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        HBox hb = new HBox();
        Army army1 = ActiveArmies.getActiveArmy1();
        Army army2 = ActiveArmies.getActiveArmy2();
        nameArmy1.setText(army1.getName());
        nameArmy2.setText(army2.getName());
        numUnitsArmy1.setText(String.valueOf(army1.getAllUnits().size()));
        numUnitsArmy2.setText(String.valueOf(army2.getAllUnits().size()));
        VBox.setVgrow(visualArmy1, Priority.ALWAYS);

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
        HBox hb2 = new HBox();
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

        visualArmy2.setContent(hb2);
    }
    @FXML
    public void returnHome(ActionEvent actionEvent) {

        if(simulationTimeline != null) simulationTimeline.stop();
        try {
            SceneManager.switchView("main");
        } catch (IOException e) {
            exMsg.setText(e.getMessage());
        }
    }

    @FXML
    public void skipToResults(ActionEvent actionEvent) {
        hasSkipBeenPressed = true;
    }

    @FXML
    public void simulateStart(ActionEvent actionEvent) {
        terrainInfo.setText("Terrain: " + ActiveTerrain.INSTANCE.getActiveTerrain().name());
        btnSimulate.setDisable(true);
        battleFeed.setVvalue(1.0);
        activeBattle = new Battle(ActiveArmies.getActiveArmy1(), ActiveArmies.getActiveArmy2(), ActiveTerrain.INSTANCE.getActiveTerrain());
        VBox vb = new VBox();
        battleFeed.setContent(vb);
        simulationTimeline = new Timeline(new KeyFrame(Duration.seconds(0.175), event -> { //TODO: improve to call less times -- use less memry
//todo: should user be able to change speed????
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
                try {
                    updateVisualArmy1();
                } catch (FileNotFoundException e) {
                    exMsg.setText(e.getMessage());
                }
                try {
                    updateVisualArmy2();
                } catch (FileNotFoundException e) {
                    exMsg.setText(e.getMessage());
                }
                ActiveArmies.setActiveArmy1(activeBattle.getArmyOne());
                ActiveArmies.setActiveArmy2(activeBattle.getArmyTwo());
//TODO: exeption on inf battle
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
                try {
                    updateVisualArmy1();
                } catch (FileNotFoundException e) {
                    exMsg.setText(e.getMessage());
                }
                try {
                    updateVisualArmy2();
                } catch (FileNotFoundException e) {
                    exMsg.setText(e.getMessage());
                }
                simulationTimeline.stop();
            }
            battleFeed.setVvalue(1.0);

        }));
        simulationTimeline.setCycleCount(Animation.INDEFINITE);
        simulationTimeline.play();
        battleFeed.setVvalue(1.0);
    }
}
