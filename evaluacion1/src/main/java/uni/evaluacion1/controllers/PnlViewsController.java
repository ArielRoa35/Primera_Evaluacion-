/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.evaluacion1.controllers;

import javax.swing.table.DefaultTableModel;
import ni.evaluacion1.views.panels.PnlViews;

/**
 *
 * @author WTorres
 */
public class PnlViewsController {

    public PnlViewsController(PnlViews pnlViews) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
         public DefaultTableModel mostrarPersonas(){
             int contador = 1; 
        
        String []  nombresColumnas = {"  Stock Number  ","Year","Make","Model","Style "
        ,"VIN","Exterior Color","Interior Color","Miles","Transmission","Engine","Image Path","Status"};
        
        String [] registros = new String[4];
        
        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
        
        String jvdao = "SELECT * FROM vehicleJson.dat";
             return null;
             
         }
    

        
    
    
}
