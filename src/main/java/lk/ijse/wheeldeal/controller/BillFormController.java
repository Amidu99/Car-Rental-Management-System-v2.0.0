package lk.ijse.wheeldeal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.wheeldeal.bo.BOFactory;
import lk.ijse.wheeldeal.bo.custom.BillBO;
import lk.ijse.wheeldeal.dto.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class BillFormController {
    @FXML
    private AnchorPane billPane;

    @FXML
    private Label lblCostPerKM;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblDiscountPrice;

    @FXML
    private Label lblDiscountRate;

    @FXML
    private Label lblDistance;

    @FXML
    private Label lblMembership;

    @FXML
    private Label lblReturnDate;

    @FXML
    private Label lblRideDate;

    @FXML
    private Label lblRideNo;

    @FXML
    private Label lblSubTotal;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblVehicleModel;

    @FXML
    private Label lblVehicleNo;

    BillBO billBO = (BillBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BILL);

    public void setLabels(String returnNo) {
        try {
            ReturnDTO returnDTOSet = billBO.getReturn(returnNo);
            assert returnDTOSet != null;
            String rideNo = returnDTOSet.getRideNo();
            double distance = returnDTOSet.getDistance();
            String returnDate = returnDTOSet.getReDate();

            RideDTO rideDTOSet = billBO.getRide(rideNo);
            assert rideDTOSet != null;
            String custID = rideDTOSet.getCustID();
            String rideDate = rideDTOSet.getRideDate();

            CustomerDTO customerDTOSet = billBO.getCustomer(custID);
            assert customerDTOSet != null;
            String custName = customerDTOSet.getName();
            String membCode = customerDTOSet.getMembCode();

            MembershipDTO membershipDTOSet = billBO.getMembership(membCode);
            assert membershipDTOSet != null;
            String membType = membershipDTOSet.getType();
            double discountRate = membershipDTOSet.getDiscount();

            String vehiNo = billBO.getVehicleNo(rideNo);

            VehicleDTO vehicleDTOSet = billBO.getVehicle(vehiNo);
            assert vehicleDTOSet != null;
            String vehiModel = vehicleDTOSet.getModel();
            double costPerKM = vehicleDTOSet.getCostPerKM();

            double subTotal = distance * costPerKM;
            double discountPrice = subTotal/100 * discountRate;
            double total = subTotal - discountPrice;

            lblRideNo.setText(rideNo);
            lblRideDate.setText(rideDate);
            lblReturnDate.setText(returnDate);
            lblCustName.setText(custName);
            lblMembership.setText(membType);
            lblDiscountRate.setText(""+discountRate+"%");
            lblVehicleModel.setText(vehiModel);
            lblCostPerKM.setText("Rs. "+costPerKM);
            lblVehicleNo.setText(vehiNo);
            lblDistance.setText(""+distance+" Km");
            lblSubTotal.setText("Rs. "+subTotal);
            lblDiscountPrice.setText("Rs. "+discountPrice);
            lblTotal.setText("Rs. "+total);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong\n"+e).show();
        }
    }

    public void setBill(String rideNo, String returnNo, String vehiNo, double distance) {
        try {
            String returnDate = LocalDate.now().toString();
            String date = billBO.getReturnDate(returnNo);
            if(date != null) {
                returnDate = date;
            }

            RideDTO rideDTOSet = billBO.getRide(rideNo);
            assert rideDTOSet != null;
            String custID = rideDTOSet.getCustID();
            String rideDate = rideDTOSet.getRideDate();

            CustomerDTO customerDTOSet = billBO.getCustomer(custID);
            assert customerDTOSet != null;
            String custName = customerDTOSet.getName();
            String membCode = customerDTOSet.getMembCode();

            MembershipDTO membershipDTOSet = billBO.getMembership(membCode);
            assert membershipDTOSet != null;
            String membType = membershipDTOSet.getType();
            double discountRate = membershipDTOSet.getDiscount();

            VehicleDTO vehicleDTO = billBO.getVehicle(vehiNo);
            assert vehicleDTO != null;
            String vehiModel = vehicleDTO.getModel();
            double costPerKM = vehicleDTO.getCostPerKM();

            double subTotal = distance * costPerKM;
            double discountPrice = subTotal/100 * discountRate;
            double total = subTotal - discountPrice;

            lblRideNo.setText(rideNo);
            lblRideDate.setText(rideDate);
            lblReturnDate.setText(returnDate);
            lblCustName.setText(custName);
            lblMembership.setText(membType);
            lblDiscountRate.setText(""+discountRate+"%");
            lblVehicleModel.setText(vehiModel);
            lblCostPerKM.setText("Rs. "+costPerKM);
            lblVehicleNo.setText(vehiNo);
            lblDistance.setText(""+distance+" Km");
            lblSubTotal.setText("Rs. "+subTotal);
            lblDiscountPrice.setText("Rs. "+discountPrice);
            lblTotal.setText("Rs. "+total);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong\n"+e).show();
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
            job.printPage(billPane);
            new Alert(Alert.AlertType.INFORMATION, "Successfully print the bill..").show();
            job.endJob();
        }
    }
}