package it.polito.tdp.ruzzle.model;


import java.util.ArrayList;

import java.util.List;

/*
 	Nella ricorsione, se verifichiamo delle condizioni per evitare  di esplorare strade inutili, queste
 	condizioni che verifichiamo non devono essere troppo complicate altrimenti anziche' semplficarci la
 	vita ce la andiamo ulteriormente a complicare in quanto ci imbattiamo a verificare cose troppo complicate.
 	
 	Nella ricorsione, con il for andiamo a provare tutti gli ordini possibili degli oggetti che scorriamo e quindi
 	bisogna fare attenzione e capire se e' una cosa necessaria avere tutti gli ordini o se basta l'insieme
 	di oggetti.
 	
 	
 */

/*
 	Un primo approccio (che e' quello che svolgiamo) e' prendere una parola del dizionario e andare a ricercare se e'
 	presente sulla griglia: verifico che la lettera iniziale sia presente sulla griglia, altrimenti per questa parola
 	non ha neanche senso incominciare la ricorsione. Ripeto questo per ogni parola del dizionario quando premo il
 	RisolviTutto.
 	
 	Tenere traccia del percorso per incominciare salvando le posizioni per comporre la parola corretta. Ricordarsi che
 	non si possono ripetere le lettere sulla scacchiera nella composizione. 
 	
 	Con questo approccio il livello e' il numero di lettere trovate e finisco quando ho il livello pari alla lunghezza 
 	della parola. 
 	
 	Presa una lettera, verifico che la lettera successiva sia in una delle possibili posizioni adiacenti che non ho ancora
 	utilizzato.
 	
 	Il secondo approccio (che non ci ha mostrato) e' provare tutte le strade partendo da una lettera, verificando in ogni
 	direzione in cui vado se esistono parole nel dizionario che iniziano con quello che sto costruendo. Ripetere questo per 
 	ogni lettera ed e' piu' efficiente, ma decisamente piu' complicato del precedente approccio.
 */

public class Ricerca {
	
	public List<Pos> trovaParola(String parola, Board board) {
		for(Pos p : board.getPositions()) {
			//controllo ogni posizione della scacchiera se contiene il carattere
			//iniziale della parola che devo ricercare
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				//inzio potenziale della parola, faccio ricorsione perche' sulla scacchiera
				//c'e' l'inizio della ricorsione e quindi ho l'inizio della parola
				List<Pos> percorso = new ArrayList<Pos>();
				//contiene tutte le celle se questa parola la trovo nella procedura
				//la nostra soluzione parziale contiene gia' la prima lettera
				percorso.add(p);
				//se trovo la parola ritorno il percorso, altrimenti se non la trovo
				//provando tutte le possibili strade restituisco null
				if(cerca(parola, 1, percorso, board))
					return percorso ; 
			}
		}
		
		return null ;
	}

	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board) {
		//caso terminale
		//abbiamo trovato un percorso che contiene la parola se entro qui
		if(livello == parola.length())
			return true;
		
		//dobbiamo prendere l'ultima posizione utilizzata che e' nella soluzione parziale in
		//'percorso' e prendiamo tutte le sue posizioni adiacenti (che era un metodo gia' fatto)
		//controllando solo quelle non ancora utilizzate e prendendo quelle che hanno la 
		//lettera successiva della parola che sto cercando.
		//se ho corrispondenza aggiungo la posizione adiacente al percorso.
		Pos ultima = percorso.get(percorso.size() -1);
		List<Pos> adiacenti = board.getAdjacencies(ultima) ;
		for(Pos p : adiacenti) {
			if(!percorso.contains(p) && parola.charAt(livello) == 
					board.getCellValueProperty(p).get().charAt(0)) {
				//faccio ricorsione
				percorso.add(p);
				//mi basta trovare una volta sola la parola, non devo esplorare
				//tutte le possibili soluzioni per quella parola e quindi appena
				//trovo un percorso che contiene la parola, mi basta ed esco
				if(cerca(parola, livello+1, percorso, board)) 
					return true ; //uscita rapida in caso di soluzione
				//backtraking
				percorso.remove(percorso.size() -1) ;
			}
		}
		return false;
	}
}
