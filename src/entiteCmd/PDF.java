/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteCmd;

import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import serviceCmd.ServiceCommande;
import serviceCmd.ServiceLivraison;


/**
 *
 * @author user
 */
public class PDF {
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceCommande m=new ServiceCommande();
        List<Commande> list=m.afficherCommande2();    
        document.add(new Paragraph("La liste des commandes :"));
        document.add(new Paragraph("     "));
         for(Commande u:list)
        {
            
        
        document.add(new Paragraph("Nom:"+u.getNomP()));
        document.add(new Paragraph("Date client :"+u.getDate()));
        document.add(new Paragraph("Quantite  :"+u.getQuantite()));
        document.add(new Paragraph("Prix Commande :"+u.getPrix()));
  


        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
     public void GeneratePdf2(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceLivraison m=new ServiceLivraison();
        List<Livraison> list=m.afficherLivraison2();    
        document.add(new Paragraph("La liste des Livraisons :"));
        document.add(new Paragraph("     "));
         for(Livraison u:list)
        {
            
        document.add(new Paragraph("L'ID de la commande :"+u.getCmd()));
        document.add(new Paragraph("L'adresse :"+u.getAddress_liv()));
        document.add(new Paragraph("Status:"+u.getTransporteur()));
        document.add(new Paragraph("Date de livraison :"+u.getDate_liv()));
        

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
}
