package reference_searcher;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		String nomeArticolo = "A study on container virtualization for guarantee quality of service in Cloud-of-Things";
		ParserCSV parserCsvScholar = new ParserCSV("/Users/Utente1/Desktop/"+ nomeArticolo +".csv");
		ParserCSV parserCsvScopus = new ParserCSV("/Users/Utente1/Desktop/scopus-4.csv");
		ParserCSV create = new ParserCSV();
		DBgestore gestoreDB = new DBgestore(""); //Inserisci la stringa di connessione del DB al posto delle ""
		int numCit_scholar = 0;
		int numCit_scopus = 0;
		boolean validazione = false;
		String nomePaper_scholar = "";		
		Scholar paper_scholar = new Scholar();
		String nomePaper_scopus = "";
		Scopus paper_scopus = new Scopus();
		Paper paperPadre = new Paper();
		String nomePaper_padre = "";
		ArrayList<String> ListaConfronto1 = new ArrayList<String>();
		ArrayList<String> ListaConfronto2 = new ArrayList<String>();
		ArrayList<String> listaFinaleDifferenze = new ArrayList<String>();
		
		
		//LETTURA, INSERIMENTO INFORMAZIONI RELATIVE AL FILE DI SCHOLAR 
		String[] scholarPaper = new String[0];
		scholarPaper =	parserCsvScholar.leggiCSV(); 		
		numCit_scholar = scholarPaper.length;
		
	
			
		try {
		if(numCit_scholar != 0) {
			//int id = 0;
			int id = gestoreDB.prelevaIDdalDB(2); //Modo che permette di eseguire query su determinate tabelle
			System.out.println(id);
			
			if(id != 0) { //DOPO IL CONTROLLO DEL PRIMO FILE
				id += 1;
				nomePaper_scholar = "PaperScholar_" + id;	
				nomePaper_padre = "Paper_" + id;
				paperPadre.setPaperTitle(nomePaper_padre);			
				paper_scholar.setScholarName(nomePaper_scholar);
				paper_scholar.setScholarQuotesNum(numCit_scholar);				
				List<String> stringList_scholar = new ArrayList<String>(Arrays.asList(scholarPaper));				
				paper_scholar.setQuotesListScholar((ArrayList<String>) stringList_scholar);
				ListaConfronto1 = (ArrayList<String>) stringList_scholar;
				gestoreDB.inserisciNellaTabella(nomePaper_scholar, numCit_scholar, false, 2);
			
			}
			else { //PRIMO FILE INSERITO SUCCESSIVAMENTE AL PRIMO FILE L'ESECUZIONE NON PASSERA' DA QUI 
				nomePaper_scholar = "PaperScholar_0";
				nomePaper_padre = "Paper_0";
				paperPadre.setPaperTitle(nomePaper_padre);
				paper_scholar.setScholarName(nomePaper_scholar);
				paper_scholar.setScholarQuotesNum(numCit_scholar);				
				List<String> stringList_scholar = new ArrayList<String>(Arrays.asList(scholarPaper));
				paper_scholar.setQuotesListScholar((ArrayList<String>) stringList_scholar);
				
				ListaConfronto1 = (ArrayList<String>) stringList_scholar;
				gestoreDB.inserisciNellaTabella(nomePaper_scholar, numCit_scholar, false, 2);
				System.out.println("PRIMOFILE");
				
			}
		}
	
		//LETTURA, INSERIMENTO INFORMAZIONI RELATIVE AL FILE DI SCOPUS
		String[] scopusPaper = new String[0];
		scopusPaper = parserCsvScopus.leggiCSV();
		numCit_scopus = scopusPaper.length;
		
		if(numCit_scopus != 0) {
			//int id=0;
			int id = gestoreDB.prelevaIDdalDB(1); //Modo che permette di eseguire query differenti su determinate tabelle
			System.out.println(id);
			
			if(id != 0) {
				id += 1;
				nomePaper_scopus = "PaperScopus_" + id;
				nomePaper_padre = "Paper_" + id;
				paperPadre.setPaperTitle(nomePaper_padre);
				paper_scopus.setScopusName(nomePaper_scopus);
				paper_scopus.setScopusQuotesNum(numCit_scopus);
				List<String> stringList_scopus = new ArrayList<String>(Arrays.asList(scopusPaper));
				paper_scopus.setquotesListScopus((ArrayList<String>) stringList_scopus);
				
				ListaConfronto2 = (ArrayList<String>) stringList_scopus;
				gestoreDB.inserisciNellaTabella(nomePaper_scopus, numCit_scopus, false, 1);
				System.out.println("ALTRIFILE");
				
			}
			else { //PRIMO FILE INSERITO SUCCESSIVAMENTE AL PRIMO FILE L'ESECUZIONE NON PASSERA' DA QUI 
				nomePaper_scopus = "PaperScopus_0";
				nomePaper_padre = "Paper_0";
				paperPadre.setPaperTitle(nomePaper_padre);
				paper_scopus.setScopusName(nomePaper_scopus);
				paper_scopus.setScopusQuotesNum(numCit_scopus);
				List<String> stringList_scopus = new ArrayList<String>(Arrays.asList(scopusPaper));
				paper_scopus.setquotesListScopus((ArrayList<String>) stringList_scopus);
				
				ListaConfronto2 = (ArrayList<String>) stringList_scopus;
				gestoreDB.inserisciNellaTabella(nomePaper_scopus, numCit_scopus, false, 1);
				System.out.println("PRIMOFILE");
				
			}
			
			
		}
		
	
		
		//CONFRONTO LE CITAZIONI DI SCHOLAR IN SCOPUS, PER VEDERE QUALI MANCANO IN SCOPUS 
		//ED INSERIMENTO NELLA LISTA FINALE 
		
		for(int i = 0; i < ListaConfronto1.size(); i++) {
			for(int j = 0; j < ListaConfronto2.size(); j++) {

				if(ListaConfronto1.get(i).toLowerCase().trim().compareTo(ListaConfronto2.get(j).toLowerCase().trim()) == 0){
					
					ListaConfronto1.remove(ListaConfronto1.get(i));
					
				}
			}
				
		}
		
		//LISTA FINALE CON GLI ELEMENTI PRESENTI IN SCHOLAR CHE MANCANO IN SCOPUS, IMPOSTO VALIDAZIONE A TRUE;
		listaFinaleDifferenze = ListaConfronto1;
		System.out.println(listaFinaleDifferenze.size());
		validazione = true;
		paperPadre.setValidation(validazione);
		
		//INSERIMENTO FILE VALIDATO NELLA TABELLA PAPER
		gestoreDB.inserisciNellaTabella(nomePaper_padre, 0, false, 3);
		
		//CREAZIONE FILE EXCEL 
		create.createFileFinale("/Users/Utente1/Desktop/citazioniDifferenti_ "+ nomeArticolo +".xls", paperPadre, paper_scopus, paper_scholar, listaFinaleDifferenze,nomeArticolo);
		
		}catch(Exception ex){
			System.out.println("ERR: " + ex.getMessage());
			
		}
	}
	

}
