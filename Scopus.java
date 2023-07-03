package reference_searcher;
import java.util.ArrayList;

public class Scopus extends Paper{

	
	private String scopusName;
	private int scopusQuotesNum;
	private ArrayList<String> quotesListScopus;
	
	
	Scopus(int id, String titolo,boolean validazione, String scopuname, int quotesnumscopus, ArrayList<String> quotesListScopus ){
		super(id,titolo,validazione);
		this.scopusName = scopuname;
		this.scopusQuotesNum = quotesnumscopus;
		this.quotesListScopus =quotesListScopus;
		
		
	}
	
	Scopus() {
		
	}
	
	public String getScopusName() {
		return scopusName;
		
	}
	
	public int getScopusQuotesNum() {
		return scopusQuotesNum;
	}
	
	public ArrayList<String> getQuotesListScopus(){
		return quotesListScopus;
	}
	
	public void setScopusName(String scopusName) {
		this.scopusName = scopusName;
	}
	
	public void setScopusQuotesNum(int scopusQuotesNum) {
		this.scopusQuotesNum = scopusQuotesNum;		
	}
	
	public void setquotesListScopus(ArrayList<String> quotesListScopus) {
		this.quotesListScopus = quotesListScopus;
	}
	
}
