package lk.ijse.wheeldeal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.wheeldeal.bo.BOFactory;
import lk.ijse.wheeldeal.bo.custom.EmployeeBO;
import lk.ijse.wheeldeal.dto.EmployeeDTO;
import lk.ijse.wheeldeal.dto.tm.EmployeeTM;
import lk.ijse.wheeldeal.util.Clock;
import lk.ijse.wheeldeal.util.Navigation;
import lk.ijse.wheeldeal.util.RegExPatterns;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    @FXML
    private Label lblNextEmpID;

    @FXML
    private Label lblInvalidEmployeeID;

    @FXML
    private Label lblInvalidName;

    @FXML
    private Label lblInvalidJob;

    @FXML
    private Label lblInvalidTel;

    @FXML
    private Label lblClock;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpJob;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpTel;

    @FXML
    public TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane root;

    //with Factory Design Pattern
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Clock.setClock(lblClock);
        generateNextEmployeeID();
        setCellValueFactory();
        getAll();
    }

    private void generateNextEmployeeID() {
        try{
            String nextID = employeeBO.generateNextID();
            lblNextEmpID.setText(nextID);
            txtEmpId.setText(nextID);
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("EmpID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("JobRole"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
    }

    private void getAll() {
        try {
            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
            List<EmployeeDTO> empList = employeeBO.getAllEmployee();

            for(EmployeeDTO employeeDTO : empList) {
                obList.add(new EmployeeTM(
                        employeeDTO.getEmpID(),
                        employeeDTO.getName(),
                        employeeDTO.getJobRole(),
                        employeeDTO.getTel()
                ));
            }
            tblEmployee.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something Went wrong\n"+e).show();
        }
    }

    private void setDefaultWarnings() {
        lblInvalidEmployeeID.setVisible(false);
        lblInvalidName.setVisible(false);
        lblInvalidJob.setVisible(false);
        lblInvalidTel.setVisible(false);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigateToDashBoard(root);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        tblEmployee.getSelectionModel().clearSelection();
        txtEmpId.clear();
        txtEmpName.clear();
        txtEmpJob.clear();
        txtEmpTel.clear();
        setDefaultWarnings();
        generateNextEmployeeID();
        getAll();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        setDefaultWarnings();
        if(!txtEmpId.getText().isEmpty() && !txtEmpName.getText().isEmpty() && !txtEmpJob.getText().isEmpty() && !txtEmpTel.getText().isEmpty()){
            boolean isEmployeeIDMatched = RegExPatterns.getEmployeeIDPattern().matcher(txtEmpId.getText()).matches();
            boolean isNameMatched = RegExPatterns.getNamePattern().matcher(txtEmpName.getText()).matches();
            boolean isJobMatched = RegExPatterns.getNamePattern().matcher(txtEmpJob.getText()).matches();
            boolean isTelMatched = RegExPatterns.getTelPattern().matcher(txtEmpTel.getText()).matches();
            String empId;
            String name;
            String jobRole;
            String tel;
            if(isEmployeeIDMatched) {
                if (isNameMatched) {
                    if (isJobMatched) {
                        if (isTelMatched) {
                            empId = txtEmpId.getText();
                            name = txtEmpName.getText();
                            jobRole = txtEmpJob.getText();
                            tel = txtEmpTel.getText();
                            boolean added = false;
                            try {
                                added = employeeBO.addEmployee(new EmployeeDTO(empId, name, jobRole, tel));
                            } catch (SQLException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.ERROR, "Something went wrong:\nNot Added.\n"+e).show();
                            }
                            if(added){
                                new Alert(Alert.AlertType.INFORMATION, "Employee added successfully..").showAndWait();
                                btnResetOnAction(event);
                            }
                        }else{ setDefaultWarnings(); lblInvalidTel.setVisible(true); txtEmpTel.requestFocus(); }
                    }else{ setDefaultWarnings(); lblInvalidJob.setVisible(true); txtEmpJob.requestFocus(); }
                }else{ setDefaultWarnings(); lblInvalidName.setVisible(true); txtEmpName.requestFocus(); }
            }else{ setDefaultWarnings(); lblInvalidEmployeeID.setVisible(true); txtEmpId.requestFocus(); }
        }else{ new Alert(Alert.AlertType.ERROR, "Fields cannot be empty").show(); }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        setDefaultWarnings();
        if(!txtEmpId.getText().isEmpty() && !txtEmpName.getText().isEmpty() && !txtEmpJob.getText().isEmpty() && !txtEmpTel.getText().isEmpty()){
            boolean isEmployeeIDMatched = RegExPatterns.getEmployeeIDPattern().matcher(txtEmpId.getText()).matches();
            boolean isNameMatched = RegExPatterns.getNamePattern().matcher(txtEmpName.getText()).matches();
            boolean isJobMatched = RegExPatterns.getNamePattern().matcher(txtEmpJob.getText()).matches();
            boolean isTelMatched = RegExPatterns.getTelPattern().matcher(txtEmpTel.getText()).matches();
            String empId;
            String name;
            String jobRole;
            String tel;
            if(isEmployeeIDMatched) {
                if (isNameMatched) {
                    if (isJobMatched) {
                        if (isTelMatched) {
                            empId = txtEmpId.getText();
                            name = txtEmpName.getText();
                            jobRole = txtEmpJob.getText();
                            tel = txtEmpTel.getText();
                            boolean updated = false;
                            try {
                                updated = employeeBO.updateEmployee(new EmployeeDTO(empId, name, jobRole, tel));
                            } catch (SQLException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.ERROR, "Something went wrong:\nNot Updated.\n"+e).show();
                            }
                            if(updated){
                                new Alert(Alert.AlertType.INFORMATION, "Employee updated successfully..").showAndWait();
                                btnResetOnAction(event);
                            }
                        }else{ setDefaultWarnings(); lblInvalidTel.setVisible(true); txtEmpTel.requestFocus(); }
                    }else{ setDefaultWarnings(); lblInvalidJob.setVisible(true); txtEmpJob.requestFocus(); }
                }else{ setDefaultWarnings(); lblInvalidName.setVisible(true); txtEmpName.requestFocus(); }
            }else{ setDefaultWarnings(); lblInvalidEmployeeID.setVisible(true); txtEmpId.requestFocus(); }
        }else{ new Alert(Alert.AlertType.ERROR, "Fields cannot be empty").show(); }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        btnDelete.setOnAction((e) -> {
            String empId = txtEmpId.getText();
            if(!empId.isEmpty()){
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete?", yes, no).showAndWait();
                if (result.orElse(no) == yes) {
                    boolean deleted = false;
                    try {
                        deleted = employeeBO.deleteEmployee(empId);
                    } catch (SQLException | ClassNotFoundException ee) {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong:\nNot Deleted.\n"+ee).show();
                    }
                    if(deleted){
                        new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully.").showAndWait();
                        btnResetOnAction(event);
                    }else{new Alert(Alert.AlertType.ERROR, "Invalid Employee:\nPlease insert a valid Employee ID").show();}
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Field cannot be empty:\nPlease enter the Employee ID").show();
            }
        });
    }

    public void txtEmpIdOnAction(ActionEvent actionEvent) {
        String empID = txtEmpId.getText();
        if(!empID.isEmpty()){
            EmployeeDTO employeeDTO = null;
            try {
                employeeDTO = employeeBO.searchEmployee(empID);
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong:\n"+e).show();
            }
            if(employeeDTO != null){
                txtEmpName.setText(employeeDTO.getName());
                txtEmpJob.setText(employeeDTO.getJobRole());
                txtEmpTel.setText(employeeDTO.getTel());
            }else{
                new Alert(Alert.AlertType.INFORMATION, "This Employee ID is not available").show();
                txtEmpName.requestFocus();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Field cannot be empty:\nPlease enter the Employee ID").show();
            txtEmpId.requestFocus();
        }
    }

    public void tblEmployeeOnAction(MouseEvent event) {
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtEmpId.setText(newSelection.getEmpID());
                txtEmpName.setText(newSelection.getName());
                txtEmpJob.setText(newSelection.getJobRole());
                txtEmpTel.setText(newSelection.getTel());
            }
        });
    }

    public void txtEmpNameOnAction(ActionEvent actionEvent) {
        txtEmpJob.requestFocus();
    }

    public void txtEmpJobOnAction(ActionEvent actionEvent) {
        txtEmpTel.requestFocus();
    }
}