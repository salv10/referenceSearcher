package reference_searcher;

import java.sql.*;

public class DBgestore {
	/*String dbName;
	String dbUrl;
	String dbUser;
	String dbPass;*/
	private String conn;
	
	DBgestore( String connessione){
		
	
		this.conn = connessione;
		
	}
	
	DBgestore(){
		
	}
	
	public String getConn() {
		return conn;
	}
	
	public void setConn(String conn) {
		this.conn = conn;
	}
	
	//Permette di effettuare la connessione al DB
	public Connection connessioneDB(String conn) throws SQLException {
		
		Connection cn;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			
		} 
		cn = DriverManager.getConnection(this.conn);
		
		return cn;

		
	}
	
	//Metodo che permette di inserire dei dati nella tabella
	public void inserisciNellaTabella(String nome, int citnum, boolean validazione, int modo) throws SQLException {
		
		Statement st;
		int rs;
		String query = "";
		Connection cn;

		
		cn = connessioneDB(this.conn);
		//Modo permette di inserire i dati nelle varie tabelle
		switch(modo) {
		
		case 1: // inserimento paper scopus
			query = "INSERT INTO Scopus (scopusName,numCit) VALUES ('" + nome + "'," + citnum + ")";
			break;
		case 2: // inserimento paper scholar
			query = "INSERT INTO Scholar (nameScholar,numCit) VALUES ('" + nome + "'," + citnum + ")";
			break;
		case 3: // inserimento paper confrontato
			query ="INSERT INTO Paper (nomePaper,validazione) VALUES ('" + nome + "'," + validazione + ")";
			break;
		}
		
		
		try {
			st = cn.createStatement();
			rs = st.executeUpdate(query);
			
			//return "ok";
			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch
		cn.close(); // chiusura connessione
		
		
	}
	
	//Metodo per l'estrapolazione dei dati dal DB
	public int prelevaIDdalDB(int modo) throws SQLException {
		
		Statement st;
		ResultSet rs;
		Connection conn;
		String query = "";
		int ID = 0;
		
	
		
		conn = connessioneDB(this.conn);
		//Modo permette di eseguire le query su tabelle differenti
		switch(modo) {
		
		case 1: // Preleva da scopus
			query = "SELECT ID FROM Scopus ORDER BY ID desc LIMIT 1";
			break;
		case 2: // preleva da scholar 
			query = "SELECT IDScholar FROM Scholar ORDER BY IDScholar desc LIMIT 1";
			break;
		}
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				  
				  ID = rs.getInt(1);
				  }
			return ID;
		}catch(SQLException e) {
			System.out.println("errore:" + e.getMessage());
			return 0;
		}
		
		

	
	}
	
	
	
	
}

	


