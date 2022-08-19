package pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LibraryPage_OR {
		
	@FindBy(xpath = "//span[@class='course_title']")
	List<WebElement> TitlesInLibrary;
	
	public List<WebElement> libraryTitles(){
		return TitlesInLibrary;
	}
	
	@FindBy(xpath = "//button[@id='dropdown-basic-button']")
	WebElement accountBtn;
	
	public WebElement accountClick() {
		return accountBtn;
	}
	
	@FindBy(xpath = "//a[@class='dropdown-item']")
	List<WebElement> myAccountItem;
	
	public List<WebElement> accountItemCheck(){
		return myAccountItem;
	}

}
