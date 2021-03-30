package uni.evaluacion1.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import uni.evaluacion1.backend.dao.implementation.JsonVehicleDaoImpl;
import uni.evaluacion1.backend.pojo.Vehicle;
import uni.evaluacion1.views.panels.PnlViews;

public class PnlViewsController {
 
    private PnlViews pnlViews;
    private DefaultTableModel  tblModelViews;
    private JsonVehicleDaoImpl jvdao;

    public PnlViewsController(PnlViews pnlViews) throws FileNotFoundException, IOException{
        this.pnlViews = pnlViews;
        initComponent();
    }
    
    private void initComponent() throws FileNotFoundException, IOException{
        
        int cont = 0;
        jvdao = new JsonVehicleDaoImpl();
        LinkedList<Vehicle> lista = new LinkedList<>(jvdao.getAll());
        String[][] matriz = new String[lista.size()][14];
        
        for (Vehicle e : lista) {
            
            matriz[cont][0] = String.valueOf(e.getStockNumber());
            matriz[cont][1] = String.valueOf(e.getYear());
            matriz[cont][2] = e.getMake();
            matriz[cont][3] = e.getModel();
            matriz[cont][4] = e.getStyle();
            matriz[cont][5] = e.getVin();
            matriz[cont][6] = e.getExteriorColor();
            matriz[cont][7] = e.getInteriorColor();
            matriz[cont][8] = e.getMiles();
            matriz[cont][9] = String.valueOf(e.getPrice());
            matriz[cont][10] = e.getTransmission().toString();
            matriz[cont][11] = e.getEngine();
            matriz[cont][12] = e.getImage();
            matriz[cont][13] = e.getStatus();
            cont++;
        }
        
        tblModelViews = new DefaultTableModel(matriz, new String [] {
                "Stock Number", "Year", "Make", "Model", "Style", "VIN", "Exterior color", "Interior color", "Miles", "Price", "Transmission", "Engine", "Image Path", "Status"
            });
        
        pnlViews.getTblViews().setModel(tblModelViews);
    }
}
