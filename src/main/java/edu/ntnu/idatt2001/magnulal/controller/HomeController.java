package edu.ntnu.idatt2001.magnulal.controller;

import edu.ntnu.idatt2001.magnulal.utils.ActiveTerrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import static edu.ntnu.idatt2001.magnulal.utils.TerrainType.*;

/**
 * TODO COMMENT
 */
public class HomeController {


    @FXML
    private Label labelArmy2;
    @FXML
    private Label labelArmy1;
    @FXML
    private Label exMsg;
    @FXML
    private Label comArmy1;
    @FXML
    private Button btnSimBattle;
    @FXML
    private Button btnTerrainRight;
    @FXML
    private Label comArmy2;
    @FXML
    private Button btnSaveArmy2;
    @FXML
    private Label cavArmy1;
    @FXML
    private Button btnViewArmy1;
    @FXML
    private Label cavArmy2;
    @FXML
    private Button btnViewArmy2;
    @FXML
    private Button btnSaveArmy1;
    @FXML
    private Label infArmy1;
    @FXML
    private Label infArmy2;
    @FXML
    private Label ranArmy1;
    @FXML
    private Label ranArmy2;
    @FXML
    private Label currentTerrain;
    @FXML
    private Label lblPathArmy2;
    @FXML
    private Button btnTerrainLeft;
    @FXML
    private Label lblPathArmy1;
    @FXML
    private Button btnLoadArmy2;


    @FXML
    public void initialize(){
        ActiveTerrain.INSTANCE.setActiveTerrain(FOREST);
        currentTerrain.setText(fetchActiveTerrain());
    }

    @FXML
    public void rotateTerrainRight(ActionEvent actionEvent){
        currentTerrain.setText(rotateActiveTerrain(1));
    }

    @FXML
    public void rotateTerrainLeft(ActionEvent actionEvent){
        currentTerrain.setText(rotateActiveTerrain(-1));
    }

    private String fetchActiveTerrain(){
        return ActiveTerrain.INSTANCE.getActiveTerrain().toString();
    }

    private String rotateActiveTerrain(int rotationValue){
        ActiveTerrain.INSTANCE.rotateTerrain(rotationValue);
        return fetchActiveTerrain();
    }

}
