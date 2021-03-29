package uni.evaluacion1.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import uni.evaluacion1.backend.dao.implementation.JsonVehicleDaoImpl;
import uni.evaluacion1.backend.pojo.Vehicle;
import uni.evaluacion1.backend.pojo.VehicleSubModel;
import uni.evaluacion1.views.panels.PnlVehicle;

public class PnlVehicleController {

    private PnlVehicle pnlVehicle;
    private Gson gson;
    private List<VehicleSubModel> vehicleSubModels;
    private DefaultComboBoxModel cmbModelMake;
    private DefaultComboBoxModel cmbModelModel;
    private DefaultComboBoxModel cmbModelYear;
    private DefaultComboBoxModel cmbModelEColor;
    private DefaultComboBoxModel cmbModelIColor;
    private DefaultComboBoxModel cmbModelStatus;
    private String status[] = new String[]{"Active","Not available","Mantainance"};
    private JFileChooser fileChooser;
    private JsonVehicleDaoImpl jvdao;
    
    public PnlVehicleController(PnlVehicle pnlVehicle) throws FileNotFoundException {
        this.pnlVehicle = pnlVehicle;
        initComponent();
    }

    private void initComponent() throws FileNotFoundException {
        gson = new Gson();
        jvdao = new JsonVehicleDaoImpl();
        JsonReader jreader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))
        ));
        
        Type listType = new TypeToken<ArrayList<VehicleSubModel>>(){}.getType();
        vehicleSubModels = gson.fromJson(jreader, listType);
        
        List<String> makes = vehicleSubModels.stream()
                .map(v -> v.getMake())
                .collect(Collectors.toList());
        List<String> models = vehicleSubModels.stream()
                .map(v -> v.getModel()).collect(Collectors.toList());
        List<String> years = vehicleSubModels.stream()
                .map(v -> v.getYear()).collect(Collectors.toList());
        List<String> colors = vehicleSubModels.stream()
                .map(v -> v.getColor()).collect(Collectors.toList());
        
        cmbModelMake = new DefaultComboBoxModel<>(makes.toArray());
        cmbModelModel = new DefaultComboBoxModel(models.toArray());
        cmbModelYear = new DefaultComboBoxModel(years.toArray());
        cmbModelEColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelIColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelStatus = new DefaultComboBoxModel(status);
        
        pnlVehicle.getCmbMake().setModel(cmbModelMake);
        pnlVehicle.getCmbModel().setModel(cmbModelModel);
        pnlVehicle.getCmbYear().setModel(cmbModelYear);
        pnlVehicle.getCmbEColor().setModel(cmbModelEColor);
        pnlVehicle.getCmbIColor().setModel(cmbModelIColor);
        pnlVehicle.getCmbStatus().setModel(cmbModelStatus);
        
        pnlVehicle.getTxtStock().requestFocus();
        
        pnlVehicle.getBtnBrowse().addActionListener((e)->{
            btnBrowseActionListener(e);
        });
        
        pnlVehicle.getBtnSave().addActionListener((e)->{
            btnSaveActionListener(e);
        });
        
    }

    private void btnSaveActionListener(ActionEvent e){
        int stock, year;
        String make, model, style, vin, eColor, iColor, miles, engine, image, status;
        float price;
        Vehicle.Transmission transmission = Vehicle.Transmission.AUTOMATIC;
        
        if(pnlVehicle.getTxtStock().getText().isEmpty() || pnlVehicle.getTxtStyle().getText().isEmpty() 
                || pnlVehicle.getTxtEngine().getText().isEmpty() || pnlVehicle.getTxtImage().getText().isEmpty()
                || pnlVehicle.getFmtVin().getText().isEmpty() || pnlVehicle.getSpnMiles().getToolTipText().isEmpty() 
                ||pnlVehicle.getSpnPrice().getToolTipText().isEmpty() || pnlVehicle.getCmbEColor().getToolTipText().isEmpty() 
                || pnlVehicle.getCmbIColor().getToolTipText().isEmpty() || pnlVehicle.getCmbMake().getToolTipText().isEmpty()
                || pnlVehicle.getCmbModel().getToolTipText().isEmpty() || pnlVehicle.getCmbStatus().getToolTipText().isEmpty()
                || pnlVehicle.getCmbYear().getToolTipText().isEmpty()){
            pnlVehicle.getTxtStock().setBorder(new LineBorder(Color.RED, 2));
            pnlVehicle.getTxtStyle().setBorder(new LineBorder(Color.RED, 2));
            pnlVehicle.getTxtEngine().setBorder(new LineBorder(Color.RED, 2));
            pnlVehicle.getTxtImage().setBorder(new LineBorder(Color.RED, 2));
            pnlVehicle.getSpnMiles().setBorder(new LineBorder(Color.RED, 2));
            pnlVehicle.getSpnPrice().setBorder(new LineBorder(Color.RED, 2));
            pnlVehicle.getFmtVin().setBorder(new LineBorder(Color.RED, 2));
            JOptionPane.showMessageDialog(pnlVehicle, "Please don't leave empty apaces");
            
            return;
        }
            
        
            
        
        
        
        stock = Integer.parseInt(pnlVehicle.getTxtStock().getText());
        year = Integer.parseInt(pnlVehicle.getCmbYear().getSelectedItem().toString());
        make = pnlVehicle.getCmbMake().getSelectedItem().toString();
        model = pnlVehicle.getCmbModel().getSelectedItem().toString();
        style = pnlVehicle.getTxtStyle().getText();
        vin = pnlVehicle.getFmtVin().getText();
        eColor = pnlVehicle.getCmbEColor().getSelectedItem().toString();
        iColor = pnlVehicle.getCmbIColor().getSelectedItem().toString();
        miles = pnlVehicle.getSpnMiles().getModel().getValue().toString();
        price = Float.parseFloat(pnlVehicle.getSpnPrice().getModel().getValue().toString());
        engine = pnlVehicle.getTxtEngine().getText();
        image = pnlVehicle.getTxtImage().getText();
        status = pnlVehicle.getCmbStatus().getSelectedItem().toString();
        transmission = pnlVehicle.getRbtnAut().isSelected() ?
                transmission : Vehicle.Transmission.MANUAL;
                
        Vehicle v = new Vehicle(stock, year, make, model, 
                style, vin, eColor, iColor, miles, price, transmission, engine, image, status);
        
        try {
            jvdao.create(v);
            JOptionPane.showMessageDialog(null, "Vehicle saved successfully.", 
                    "Information message", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), 
                    "Error message", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PnlVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void btnBrowseActionListener(ActionEvent e){
        fileChooser = new JFileChooser();
        
        int option = fileChooser.showOpenDialog(null);
        if(option == JFileChooser.CANCEL_OPTION){
            return;
        }
        
        File file = fileChooser.getSelectedFile();
        if(!file.exists()){
            JOptionPane.showMessageDialog(null, "Image does not exists", 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        pnlVehicle.getTxtImage().setText(file.getPath());        
    }
}
