package reference_searcher;
import java.util.ArrayList; 

public class Scholar extends Paper{
	
	private String scholarName;
	private int scholarQuotesNum;
	private ArrayList<String> quotesListScholar;

	Scholar(int id,String titolo, boolean validazione, String scholarname, int scholarquotesnum, ArrayList<String> quotesListScholar){
		super(id,titolo,validazione);
		this.scholarName = scholarname;
		this.scholarQuotesNum =scholarquotesnum;
		this.quotesListScholar = quotesListScholar;
	
	}
	
	Scholar(){
		
	}
	
	public String getScholarName() {
		return scholarName;
	}
	
	public int getScholarQuotesNum() {
		return scholarQuotesNum;
	}
	
	public ArrayList<String> getQuotesListScholar(){
		return quotesListScholar;
	}
	
	public void setScholarName(String scholarName) {
		this.scholarName = scholarName;
	}
	
	public void setScholarQuotesNum(int scholarQuotesNum) {
		this.scholarQuotesNum = scholarQuotesNum;
	}
	
	public void setQuotesListScholar(ArrayList<String> quotesListScholar) {
		this.quotesListScholar = quotesListScholar;
	}
}
