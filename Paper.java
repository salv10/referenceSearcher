package reference_searcher;

public class Paper {
	
	private int IDpaper;
	private String paperTitle;
	private boolean validation;
	
	Paper(int IDpaper, String paperTitle, boolean validation){
		this.IDpaper = IDpaper;
		this.paperTitle = paperTitle;
		this.validation = validation;
	}
	
	Paper(){
		
	}
	
	public int getID() {
		return IDpaper;
	}
	
	public String getPaperTitle() {
		return paperTitle;
	}
	
	public boolean getValidation() {
		return validation;
	}
	
	public void setID(int ID) {
		this.IDpaper = ID;
	}
	
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	
	public void setValidation(boolean validation) {
		this.validation = validation;
	}
}
