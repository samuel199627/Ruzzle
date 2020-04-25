package it.polito.tdp.ruzzle.db;

import java.sql.Connection;

import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 	Fino ad ora avevamo una connessione al database per ogni query che si doveva eseguire e poi
 	questa connessione veniva chiusa. Il problema e' che se tipo le query servono nella ricorsione
 	allora il sistema operativo ha un problema perche' in un certo tempo non riesce ad aprire e 
 	chiudere piu' di un certo numero di connessioni. 
 	
 	Quindi usiamo il concetto di connection pooling. 
 	all'inizio apriamo un certo numero di connessioni che mi tengo libero e per ogni query che mi
 	servono ne prendiamo una in prestito da quelle aperte qui e poi la restituiamo alla fine. In 
 	questo modo tutte le connessioni vengono aperte qui all'inizio (quindi si richiede un po' piu'
 	di tempo all'inizio), ma poi non ho nessun tipo di problema e posso fare quante query voglio
 	perche' prendo solo in prestito e restituisco le connessioni che sono state create senza aprirne
 	effettivamente di nuove andando alla lunga a velocizzare notevolmente il programma.
 	
 	Per fare quanto raccontato sopra ci serviamo della libreria HikariCP in cui creiamo l'oggetto
 	datasource in cui lui crea e tiene le connessioni iniziali e ce le da in prestito quando servono
 	e noi gliele ridiamo quando abbiamo finito di fare quello che dobbiamo nel DAO.
 */

public class DBConnect {
	private static final String jdbcURL = "jdbc:mysql://localhost/dizionario";
	private static HikariDataSource ds;
	
	public static Connection getConnection() {
		if(ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcURL);
			config.setUsername("root");
			config.setPassword("Caraglio199627");
			
			config.addDataSourceProperty("cachePrepStmts", true);
			config.addDataSourceProperty("prepStmtChacheSize", 250);
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			ds = new HikariDataSource(config);
		}
		
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Errore di connessione ad db");
			throw new RuntimeException(e);
		}
	}
}
