/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promotion.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import pi.entities.Regime;
import pi.utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mohta
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private Label over3000;
    @FXML
    private Label under3000;
    @FXML
    private Button PDF;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PieChart.Data qtr1;
        try {
            qtr1 = new PieChart.Data("Regime avec plus de 3000 calories ",countoverMoy() );
           PieChart.Data qtr2 = new PieChart.Data("Regime avec moins de 3000 calories ",countunderMoy() );
             piechart.getData().addAll(qtr1,qtr2);
             over3000.setText(String.valueOf(countoverMoy()));
          under3000.setText(String.valueOf(countunderMoy()));
             
             
             
             
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    public double countunderMoy() throws SQLException{
       double x=0;
        Connection myconn =MyConnection.getInstance();
     String query ="  SELECT COUNT(*) AS total_utilisateurs FROM regime WHERE nb_calorie < 3000";
     
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); 
          
          
         ResultSet rs1 = ste.executeQuery();
         while(rs1.next()){
        x =  rs1.getDouble(1);
        
       
        
         }
         
        
        
         
          
          
         } catch (SQLException ex) {
            System.out.println(ex);
            
        }
return x;
         }
    
    
    
     public double countoverMoy() throws SQLException{
       double x=0;
        Connection myconn =MyConnection.getInstance();
     String query ="  SELECT COUNT(*) AS total_utilisateurs FROM regime WHERE nb_calorie > 3000";
     
     
     PreparedStatement ste= myconn.prepareStatement(query);
     ResultSet rs1 = ste.executeQuery();
     while(rs1.next()){
         x =  rs1.getDouble(1);
         
         
     }
return x;
         }
     
     public void printtopdf() throws FileNotFoundException, DocumentException{
         ListData dc = new ListData();
         try{
         String fileName = "C:/Gen PDF/LIST.pdf";
         Document document = new Document();
  PdfWriter.getInstance(document, new FileOutputStream(fileName));
  
document.open();
for (Regime regime : dc.getPersons()) {
    // Write the user information to the PDF document
    document.add(new Paragraph("Name: " + regime.getName()));
    document.add(new Paragraph("Description: " + regime.getDiscription()));
    document.add(new Paragraph("Nombre de Cals: " + regime.getCalories()));
     document.add(new Paragraph("Start Date: " + regime.getStart_date()));
          document.add(new Paragraph("End Date: " + regime.getEnd_date()));
    document.add(new Paragraph("------------------------------------------------------------------------------"));
}

document.close();

JOptionPane.showMessageDialog(null , "PDF LIST CREATED");
         } catch (DocumentException | FileNotFoundException e){
             System.out.println(e);
         }
          


     }

    @FXML
    private void OnBtnClicked(ActionEvent event) throws FileNotFoundException, DocumentException {
        if (event.getSource() == PDF )
        {
            printtopdf();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pi/view/list.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }
}

