package lk.ijse.wheeldeal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.wheeldeal.bo.BOFactory;
import lk.ijse.wheeldeal.bo.custom.RideDriverBO;
import lk.ijse.wheeldeal.dto.DriverDTO;
import lk.ijse.wheeldeal.dto.RideDriverDTO;
import lk.ijse.wheeldeal.dto.tm.DriverTM;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RideDriverFormController implements Initializable {
    @FXML
    private TextField txtRideNo;

    @FXML
    private TextField txtDriverID;

    @FXML
    private DatePicker txtRideDate;

    @FXML
    private TableView<DriverTM> tblDriver;

    @FXML
    private TableColumn<?, ?> colDriverID;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private Label lblDriver;

    RideDriverBO rideDriverBO = (RideDriverBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RIDE_DRIVER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colDriverID.setCellValueFactory(new PropertyValueFactory<>("DriverID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
    }

    public void getAllAvailableDrivers(LocalDate rideDate) {
        try {
            ObservableList<DriverTM> obList = FXCollections.observableArrayList();
            List<DriverDTO> driverDTOList = rideDriverBO.getAllAvailableDrivers(rideDate);

            for(DriverDTO driverDTO : driverDTOList) {
                obList.add(new DriverTM(
                        driverDTO.getDriverID(),
                        driverDTO.getName(),
                        driverDTO.getLocation(),
                        driverDTO.getTel(),
                        driverDTO.getAvailability()
                ));
            }
            tblDriver.setItems(obList);
            lblDriver.setText("Available Drivers in "+txtRideDate.getValue().toString());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Went wrong\n"+e).show();
        }
    }

    public void setTextfields(String rideNo, LocalDate rideDate){
        txtRideNo.setText(rideNo);
        txtRideDate.setValue(rideDate);
    }
    public void tblDriverOnAction(MouseEvent event) {
        tblDriver.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtDriverID.setText(newSelection.getDriverID());
            }
        });
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String rideNo = txtRideNo.getText();
        LocalDate rideDate = txtRideDate.getValue();
        if(!txtDriverID.getText().isEmpty() && rideDate.isAfter(LocalDate.now()) || rideDate.isEqual(LocalDate.now())){
            boolean added = false;
            try {
                String driverID = txtDriverID.getText();
                added = rideDriverBO.addRide(new RideDriverDTO(rideNo, driverID, rideDate.toString()));
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong:\nMake sure the ride is placed successfully,\nbefore add a Driver").show();
            }
            if (added) {
                new Alert(Alert.AlertType.INFORMATION, "Driver added successfully..").showAndWait();
                btnExitOnAction(actionEvent);
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Fields cannot be empty or invalid").show();
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}