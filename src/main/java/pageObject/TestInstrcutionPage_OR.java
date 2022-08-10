package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestInstrcutionPage_OR {
	
	
	@FindBy(id = "start-test")
	private List<WebElement> listCheckBoxInstrcution;
	
	
	@FindBy(css = "button.red")
	private List<WebElement> listBtnStartTest;


	public List<WebElement> getListCheckBoxInstrcution() {
		return listCheckBoxInstrcution;
	}


	public List<WebElement> getListBtnStartTest() {
		return listBtnStartTest;
	}
}
