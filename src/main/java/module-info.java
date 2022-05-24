/**
 * Module information for Wargames
 */
module edu.ntnu.idatt2001.magnulal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens edu.ntnu.idatt2001.magnulal to javafx.fxml;
    exports edu.ntnu.idatt2001.magnulal;
    exports edu.ntnu.idatt2001.magnulal.guiControllers;
    opens edu.ntnu.idatt2001.magnulal.guiControllers to javafx.fxml;
    exports edu.ntnu.idatt2001.magnulal.model.simulator;
    opens edu.ntnu.idatt2001.magnulal.model.simulator to javafx.fxml;
    exports edu.ntnu.idatt2001.magnulal.model.units;
    opens edu.ntnu.idatt2001.magnulal.model.units to javafx.fxml;
    exports edu.ntnu.idatt2001.magnulal.utils;
    opens edu.ntnu.idatt2001.magnulal.utils to javafx.fxml;
    exports edu.ntnu.idatt2001.magnulal.utils.exceptions;
    opens edu.ntnu.idatt2001.magnulal.utils.exceptions to javafx.fxml;

}