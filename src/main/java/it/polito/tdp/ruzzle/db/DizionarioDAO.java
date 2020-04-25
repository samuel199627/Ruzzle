package it.polito.tdp.ruzzle.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 	Qui in questo programma non viene usata, ma inserisco questo commento qui per completezza.
 	
 	LIMIT	n
 	
 	e' una funzione SQL che restituisce le prime 'n' righe del risultato della query che abbiamo applicato.
 	Devo logicamente avere un'idea dell'ordinamento che ho in uscita della query altrimenti non so
 	di fatto che cosa mi vado a selezionare.
 	
 	Le tabelle di relazione (tipo con chiave primaria che e' la combinazione di chiave primaria di altre
 	tabelle tipo: autore, libro, pubblicazione in qui quest'ultima e' la tabella di relazione) non le 
 	andiamo a mettere come oggetto.
 	
 	Una tabella che rappresenta un oggetto lo andiamo a creare in java inizializzandolo con gli attributi
 	che sono piu' comodamente estraibili dalla tabella, mentre attributi di relazione con altri oggetti
 	li possiamo inizializzare vuoti e li andremo poi a caricare in maniera piu' adeguata successivamente
 	per non complicare troppo l'importazione di una tabella.
 	
 	Cio' che in SQL costituisce chiave primaria, in Java deve corrispondere ad equals ed hashcode.
 	
 	Nelle relazioni bidirezionali tra tabelle che rappresentano oggetti (con le chiavi esterne) le 
 	raccontiamo il java creando direttamente l'oggetto e non passando solo la chiave esterna, perche' in
 	questa maniera rimane decisamente piu' comodo. Un esempio e' una tabella con Studente e una con Citta'
 	in cui nella prima c'e' riferimento alla seconda e viceversa, quindi in Java dato che in generale
 	si parla di oggetti e non di tipi di dato predefiniti come SQL, andiamo a salvarci il riferiemento
 	all'oggetto dell'altra tabella.
 	Questo serve per evitare di scorrere ogni volta tutta l'altra tabella per risalire a che cosa faccio 
 	riferimento.
 	E' un caricamento molto piu' pesante logicamente che fa tutto subito per avere meno problemi dopo, ma
 	e' anche vero che rischiamo di caricare cose inutili. Per alleggerire un po' il tutto possiamo andarci
 	a creare gli oggetti senza caricarli completamente e poi caricarli man mano nel programma quando servono.
 	
 	Per gestire le relazioni molti a molti si usano le IdentityMap che sono delle classi che permettono
 	di evitare di salvare in oggetti diversi quello che dovrebbe essere lo stesso oggetto. Ad esempio il caso
 	era quello di Autori e Libri in cui i primi possono scrivere piu' libri e i libri possono essere scritti 
 	da piu' autori, ma tipo mentre mi importo dei libri, se un autore compare per due libri diversi, non devo
 	crearmi due oggetti diversi che rappresentano lo stesso autore. Con la IdentityMap vado a verificare che
 	prima di crearlo non sia gia' effettivamente esistente.
 	
 	
 */
public class DizionarioDAO {
	
	/*
	 	estraiamo tutte le parole del dizionario e cosi' ce le portiamo in pancia al programma
	 	per non interrogare di continuo il database.
	 */
	
	/**
	 * Ritorna tutte le parole presenti nel dizionario
	 * 
	 * @return
	 */
	public List<String> listParola() {
		List<String> result = new ArrayList<>() ;
		
		String query = "SELECT nome FROM parola ORDER BY nome" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(query) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add(res.getString("nome")) ;
			}
			
			res.close();
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;

	}

}
