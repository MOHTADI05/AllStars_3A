/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.rowset.internal.Row;
import entity.Reclamation;
import entity.Reponse;
import gestionreclamationsante.FXMain;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.ServiceReclamation;
import service.ServiceReponse;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLreclamationadminController implements Initializable {

    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation, String> tcemail;
    @FXML
    private TableColumn<Reclamation, String> tcobject;
    @FXML
    private TableColumn<Reclamation, String> tcdescription;
    @FXML
    private TableColumn<Reclamation, Date> tcdatereclamation;
    @FXML
    private TableColumn<Reclamation, Date> tcdatetraitement;
    @FXML
    private TableColumn<Reclamation, String> tcetat;
    @FXML
    private TextArea tfreponse;
    @FXML
    private TextField tfobjet;
    @FXML
    private TextField eChercher;
    @FXML
    private Button bchercher;
    
ObservableList<Reclamation> data=FXCollections.observableArrayList();
    ServiceReclamation sr=new ServiceReclamation();
    ServiceReponse srep=new ServiceReponse();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        Reclamation r=tvreclamation.getSelectionModel().getSelectedItem();
        if(r!=null){
            if(r.getEtat().equals("Traitée")){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de reponse");
                alert.setContentText("Vouz avez deja repondre a cette reclamation!");
                alert.showAndWait();
            }
            else{
                Reponse rep=new Reponse();
                rep.setDate_reponse(new Date(System.currentTimeMillis()));
                rep.setDescription(tfreponse.getText());
                rep.setObjet(tfobjet.getText());
                rep.setReclamation_id(r.getId());
                srep.ajouter(rep);
                r.setEtat("Traitée");
                r.setDate_traitement(new Date(System.currentTimeMillis()));
                sr.modifier(r.getId(), r);
                refresh();
            }
            
            
        }
    }
     public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(sr.afficher());
        tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcobject.setCellValueFactory(new PropertyValueFactory<>("objet"));
        tcetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcdatereclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        tcdatetraitement.setCellValueFactory(new PropertyValueFactory<>("date_traitement"));
        tvreclamation.setItems(data);
    }
     
    @FXML
    public void excel(ActionEvent actionEvent) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            FileChooser chooser = new FileChooser();
            // Set extension filter
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(.xls)", ".xls");
            chooser.getExtensionFilters().add(filter);

            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            workSheet.setColumnWidth(1, 25);

            HSSFFont fontBold = workbook.createFont();
            fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle styleBold = workbook.createCellStyle();
            styleBold.setFont(fontBold);

            HSSFRow row1 = workSheet.createRow((short) 0);

            row1.createCell(0).setCellValue("Email");
            row1.createCell(1).setCellValue("objet");
            row1.createCell(2).setCellValue("description");
        //    row1.createCell(3).setCellValue("Prix");

          
        ObservableList<Reclamation> listReclamations = tvreclamation.getItems();
int i = 0;
for (Reclamation r : listReclamations) {
    i++;
    HSSFRow row2 = workSheet.createRow((short) i);
    row2.createCell(0).setCellValue(r.getEmail());
    row2.createCell(1).setCellValue(r.getDescription());
    row2.createCell(2).setCellValue(r.getObjet());
}


            workSheet.autoSizeColumn(0);
            workSheet.autoSizeColumn(1);
            workSheet.autoSizeColumn(2);
     
            workbook.write(Files.newOutputStream(Paths.get("article.xls")));
            Desktop.getDesktop().open(new File("article.xls"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
  
@FXML
public void pdf(ActionEvent event) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("reclamations.pdf"));
        document.open();

        // Ajouter un titre au document
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph title = new Paragraph("Liste des réclamations", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Ajouter la TableView des réclamations au document
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        // Ajouter les en-têtes de colonnes
        PdfPCell cell1 = new PdfPCell(new Paragraph("Email"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Objet"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Description"));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);

        // Ajouter les données de la TableView
        ObservableList<Reclamation> listReclamations = tvreclamation.getItems();
        for (Reclamation r : listReclamations) {
            table.addCell(r.getEmail());
            table.addCell(r.getObjet());
            table.addCell(r.getDescription());
        }

        document.add(table);
        document.close();

        // Ouvrir le PDF généré
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(new File("reclamations.pdf"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void tri(ActionEvent event) {
        refresh(sr.triParDate());
    }
   @FXML
private void chercher(ActionEvent event) {
    ServiceReclamation rs = new ServiceReclamation();
    ObservableList<Reclamation> list = FXCollections.observableList(rs.afficher());
    ServiceReclamation e = new ServiceReclamation();

    tcobject.setCellValueFactory(new PropertyValueFactory<>("objet"));
    tcetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    
    tvreclamation.setItems(list);

    rs.afficher();
    tvreclamation.setItems(list);

    FilteredList<Reclamation> filteredData;
    filteredData = new FilteredList<>(list, b -> true);
    eChercher.textProperty().addListener(((observable, oldValue, newValue) -> {
        filteredData.setPredicate(reclamation -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            if (reclamation.getObjet().toLowerCase().contains(lowerCaseFilter) || reclamation.getEtat().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }

        });

    }));
    SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(tvreclamation.comparatorProperty());
    tvreclamation.setItems(sortedData);
}
 @FXML
    private void stat(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLstatreclamation.fxml"));

            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Gestion recalamation");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Tri(ActionEvent event) {
    }

    private void refresh(List<Reclamation> triParDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
