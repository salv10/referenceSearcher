package reference_searcher;

import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;


import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.usermodel.HSSFRow; 

public class ParserCSV {
	
	
	private String pathName;
	
	ParserCSV(String path){
		
		this.pathName = path;
	}
	
	ParserCSV(){
		
	}
	
	public String getPathName() {
		return pathName;
	}
	
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
	//Lettura del CSV in entrata
	public String[] leggiCSV() {
		
		try {
			//String[] myArray = new String[] ;
			ArrayList<String> arr = new ArrayList<String>();
			int nome_colonna = 0;
			List<String[]> r;
			try (CSVReader reader = new CSVReader(new FileReader(this.pathName))){
				r = reader.readAll();
			}
			int listIndex = 0;
			for (String[] arrays : r) {
				
				int index = 0;
				for(int i = 0; i < arrays.length -1; i++) {
					if(arrays[i].equals("Title")) {
						nome_colonna = i;
						
					}
				}
					
				arr.add(arrays[nome_colonna]); 
				
			}
			
			String[] array = arr.toArray(new String[0]);
			return array;
			
		}
		catch(Exception e) {
			System.out.println("Errore" + e.getMessage());
			//return "ERR:" + e.getMessage();
			ArrayList<String> arr1 = new ArrayList<String>();
			arr1.add("err");
			String[] arr11 = arr1.toArray(new String[0]);
			return arr11;
		}
		
		
	}
	
	//Metodo per la creazione del file excel
	public void createFileFinale(String pathNomeFile, Paper nomePaper,Scopus nomeScopus, Scholar nomeScholar, ArrayList<String> listaFinale, String nomeArticolo) {
		
		try {
			
			// Creazione del foglio excel
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("prova");
			HSSFRow rowHeader = sheet.createRow((short)0);
			rowHeader.createCell(0).setCellValue("Nome Paper");
			rowHeader.createCell(1).setCellValue("Nome Scholar");
			rowHeader.createCell(2).setCellValue("Nome Scopus");
			rowHeader.createCell(3).setCellValue("Citazioni");
			rowHeader.createCell(4).setCellValue("Nome Paper originale");
			
			ListIterator<String> my_arr = (ListIterator<String>) listaFinale.listIterator();
			int rowindex = 1;
			while(my_arr.hasNext()) {
				
				HSSFRow row = sheet.createRow(rowindex++);
				if(rowindex == 2) {
					row.createCell(0).setCellValue(nomePaper.getPaperTitle());
					row.createCell(1).setCellValue(nomeScholar.getScholarName());
					row.createCell(2).setCellValue(nomeScopus.getScopusName());
					row.createCell(4).setCellValue(nomeArticolo);
				}
				row.createCell(3).setCellValue(my_arr.next());
			}
			
			try {
				
			
			FileOutputStream fileOut = new FileOutputStream(pathNomeFile);
			workbook.write(fileOut);
			fileOut.close();  
			//workbook.close();
			
			System.out.println("OK: File generato correttamente");
			}
			catch(Exception e){
				System.out.println("Err: " + e.getMessage());
			}
		
		}
		catch(Exception ex){
			
			System.out.println("Err: " + ex.getMessage());
			
		}
		
		
	}
	
	
}

