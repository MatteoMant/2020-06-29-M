/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Adiacente;
import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaAffini"
    private Button btnCercaAffini; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxRegista"
    private ComboBox<Director> boxRegista; // Value injected by FXMLLoader

    @FXML // fx:id="txtAttoriCondivisi"
    private TextField txtAttoriCondivisi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	boxRegista.getItems().clear();
    	
    	Integer anno = boxAnno.getValue();
    	if (anno == null) {
    		txtResult.setText("Per favore selezionare un anno!\n");
    		return;
    	}
    	this.model.creaGrafo(anno);
    	txtResult.setText("Grafo creato!\n");
    	txtResult.appendText("# Vertici " + this.model.getNumVertici() + "\n");
    	txtResult.appendText("# Archi " + this.model.getNumArchi() + "\n");
    	
    	// Dopo aver creato il grafo ...
    	List<Director> registi = new LinkedList<>(this.model.getAllVertici());
    	Collections.sort(registi);
    	boxRegista.getItems().addAll(registi);
    }

    @FXML
    void doRegistiAdiacenti(ActionEvent event) {
    	Director regista = boxRegista.getValue();
    	if (regista == null) {
    		txtResult.setText("Per favore selezionare un regista!\n");
    		return;
    	} 
    	List<Adiacente> adiacenti = this.model.getVerticiAdiacenti(regista);
    	Collections.sort(adiacenti);
    	txtResult.setText("REGISTI ADIACENTI A: " + regista + "\n");
    	for (Adiacente a : adiacenti) {
    		txtResult.appendText(a + "\n");
    	}
    }

    @FXML
    void doRicorsione(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAffini != null : "fx:id=\"btnCercaAffini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxRegista != null : "fx:id=\"boxRegista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAttoriCondivisi != null : "fx:id=\"txtAttoriCondivisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        for (int anno = 2004; anno <= 2006; anno++) {
        	boxAnno.getItems().add(anno);
        }
    }
    
   public void setModel(Model model) {
    	this.model = model;
    }
    
}
