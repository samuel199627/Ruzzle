package it.polito.tdp.ruzzle;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.ruzzle.model.Model;
import it.polito.tdp.ruzzle.model.Pos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/*
	Quello che si vuole fare e' implementare il gioco Ruzzle e quindi con il pulsante Prova
	si va a verificare se la parola scritta nella parte di testo e' presente nella matrice di lettere
	mentre con RisolviTutto si va a stampare tutte le possibili parole che troviamo nella matrice
	di lettere che appartengono al dizionario italiano.
	
	Pos identifica la posizione nella griglia (riga, colonna).
	
	Board rappresenta la nostra scacchiera.
	
	Le righe e le colonne sono numerate a partire da zero e i nomi dei pulsanti li abbiamo indicati
	come il numero di riga e colonna a cui fanno riferimento.
 */

public class FXMLController {

	private Model model ; 
	List<Pos> percorso=null;
	
	//corrispondenza bottoni dell'interfaccia grafica <-> caselle della Board 
	private Map<Pos,Button> letters ;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="let00"
    private Button let00; // Value injected by FXMLLoader

    @FXML // fx:id="let01"
    private Button let01; // Value injected by FXMLLoader

    @FXML // fx:id="let02"
    private Button let02; // Value injected by FXMLLoader

    @FXML // fx:id="let03"
    private Button let03; // Value injected by FXMLLoader

    @FXML // fx:id="let10"
    private Button let10; // Value injected by FXMLLoader

    @FXML // fx:id="let11"
    private Button let11; // Value injected by FXMLLoader

    @FXML // fx:id="let12"
    private Button let12; // Value injected by FXMLLoader

    @FXML // fx:id="let13"
    private Button let13; // Value injected by FXMLLoader

    @FXML // fx:id="let20" 
    private Button let20; // Value injected by FXMLLoader

    @FXML // fx:id="let21"
    private Button let21; // Value injected by FXMLLoader

    @FXML // fx:id="let22"
    private Button let22; // Value injected by FXMLLoader

    @FXML // fx:id="let23"
    private Button let23; // Value injected by FXMLLoader

    @FXML // fx:id="let30"
    private Button let30; // Value injected by FXMLLoader

    @FXML // fx:id="let31"
    private Button let31; // Value injected by FXMLLoader

    @FXML // fx:id="let32"
    private Button let32; // Value injected by FXMLLoader

    @FXML // fx:id="let33"
    private Button let33; // Value injected by FXMLLoader

    @FXML // fx:id="txtStatus"
    private Label txtStatus; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtResul"
    private TextArea txtResult; // Value injected by FXMLLoader


    @FXML
    void handleProva(ActionEvent event) {
    	
    	//metodo per provare singole parole
    	
    	//refresh interfaccia grafica
    	for(Button b : letters.values()) {
    		b.setDefaultButton(false);
    	}
    	
    	
    	String parola = txtParola.getText() ;
    	//in ruzzle le parole valide sono almeno di 2 caratteri
    	if(parola.length() <= 1) {
    		txtResult.setText("Devi inserire parole di almeno 2 lettere");
    		return;
    	}
    	parola = parola.toUpperCase();
    	//controllo che ci siano solo caratteri A-Z e non ci siano caratteri numerici
    	if(!parola.matches("[A-Z]+")) {
    		txtResult.setText("Devi inserire solo caratteri alfabetici!");
    		return ;
    	}
    	
    	percorso= new ArrayList<Pos>();
    	percorso = model.trovaParola(parola);
    	
    	//coloro i bottoni del percorso
    	if(percorso != null) {
    		for(Pos p : percorso) {
    			letters.get(p).setDefaultButton(true);
    		}
    	}
    	else {
    		txtResult.setText("Parola non trovata!");
    	}
    	
    }

    @FXML
    void handleReset(ActionEvent event) {
    	//resettiamo tutte le variabili
    	model.reset();
    	//decoloriamo eventuali pulsanti che erano rimasti accesi dall'ultima ricerca 
    	//della singola parola
    	if(percorso != null) {
    		for(Pos p : percorso) {
    			letters.get(p).setDefaultButton(false);
    		}
    	}
    }
    
    @FXML
    void handleRisolvi(ActionEvent event) {
    	
    	List<String> tutte = model.trovaTutte();
    	int conta=0;
    	
    	txtResult.clear();
    	txtResult.appendText(String.format("Ho trovato %d soluzioni\n", tutte.size()));
    	for(String s : tutte) {
    		conta++;
    		txtResult.appendText(""+conta+". "+s + "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let00 != null : "fx:id=\"let00\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let01 != null : "fx:id=\"let01\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let02 != null : "fx:id=\"let02\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let03 != null : "fx:id=\"let03\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let10 != null : "fx:id=\"let10\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let11 != null : "fx:id=\"let11\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let12 != null : "fx:id=\"let12\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let13 != null : "fx:id=\"let13\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let20 != null : "fx:id=\"let20\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let21 != null : "fx:id=\"let21\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let22 != null : "fx:id=\"let22\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let23 != null : "fx:id=\"let23\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let30 != null : "fx:id=\"let30\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let31 != null : "fx:id=\"let31\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let32 != null : "fx:id=\"let32\" was not injected: check your FXML file 'Scene.fxml'.";
        assert let33 != null : "fx:id=\"let33\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model = m ;
    	
    	this.letters = new HashMap<>() ;
    	
    	//creiamo un mapping tra le posizioni sulla scacchiera e i relativi
    	//bottoni in modo che tutto si possa modificare e legare insieme
    	
    	this.letters.put(new Pos(0,0), let00) ;
    	this.letters.put(new Pos(0,1), let01) ;
    	this.letters.put(new Pos(0,2), let02) ;
    	this.letters.put(new Pos(0,3), let03) ;
    	
    	this.letters.put(new Pos(1,0), let10) ;
    	this.letters.put(new Pos(1,1), let11) ;
    	this.letters.put(new Pos(1,2), let12) ;
    	this.letters.put(new Pos(1,3), let13) ;

    	this.letters.put(new Pos(2,0), let20) ;
    	this.letters.put(new Pos(2,1), let21) ;
    	this.letters.put(new Pos(2,2), let22) ;
    	this.letters.put(new Pos(2,3), let23) ;
    	
    	this.letters.put(new Pos(3,0), let30) ;
    	this.letters.put(new Pos(3,1), let31) ;
    	this.letters.put(new Pos(3,2), let32) ;
    	this.letters.put(new Pos(3,3), let33) ;
    	
    	//per ogni bottone andiamo a legare la string property con il bottone
    	//in modo che il testo cambi in maniera simultanea tra la proprieta' e
    	//quello che e' il testo del bottone

    	for(Pos cell: m.getBoard().getPositions()) {
    		this.letters.get(cell).textProperty().bind(m.getBoard().getCellValueProperty(cell));
    	}
    	
    	this.txtStatus.textProperty().bind(m.statusTextProperty());
    	
    }
}
