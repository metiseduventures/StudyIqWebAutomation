package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestAttempPage_OR {

	@FindBy(css = "div.test-sections-item>button")
	private List<WebElement> listLabelTestSection;

	@FindBy(css = "div.tc-questions-status > div.tcq-status-index>div.tcq-answered>div.green")
	private List<WebElement> listQuestionStatusAnswered;

	@FindBy(css = "div.tc-questions-status > div.tcq-status-index>div.tcq-answered>div.red")
	private List<WebElement> listQuestionStatusNotAnswered;

	@FindBy(css = "div.tc-questions-status > div.tcq-status-index>div.tcq-answered>div.grey")
	private List<WebElement> listQuestionStatusNotVisited;

	@FindBy(css = "div.tc-questions-status > div.tcq-status-index>div.tcq-answered>div.purple")
	private List<WebElement> listQuestionStatusMarkForReview;

	@FindBy(css = "div.tc-questions-status > div.tcq-status-index>div.tcq-answered>div.purple-green")
	private List<WebElement> listQuestionStatusAnsweredAndMarkForReview;

	@FindBy(xpath = "//label[contains(@for,'option-')]")
	private List<WebElement> listAnswerOption;

	public List<WebElement> getListLabelTestSection() {
		return listLabelTestSection;
	}

	public List<WebElement> getListQuestionStatusAnswered() {
		return listQuestionStatusAnswered;
	}

	public List<WebElement> getListQuestionStatusNotAnswered() {
		return listQuestionStatusNotAnswered;
	}

	public List<WebElement> getListQuestionStatusNotVisited() {
		return listQuestionStatusNotVisited;
	}

	public List<WebElement> getListQuestionStatusMarkForReview() {
		return listQuestionStatusMarkForReview;
	}

	public List<WebElement> getListQuestionStatusAnsweredAndMarkForReview() {
		return listQuestionStatusAnsweredAndMarkForReview;
	}

	public List<WebElement> getListAnswerOption() {
		return listAnswerOption;
	}

	@FindBy(xpath = "//*[@id='root']/div/div/div[2]/div[5]/div[1]/div[3]/button")
	private List<WebElement> listBtnSaveNext;

	public List<WebElement> getListBtnSaveNext() {
		return listBtnSaveNext;
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[5]/div[1]/div[2]/button")
	private List<WebElement> listBtnMarkForReviewAndNext;

	public List<WebElement> getListBtnMarkForReviewAndNext() {
		return listBtnMarkForReviewAndNext;
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[5]/div[1]/div[1]/button")
	private List<WebElement> listBtnClearResponse;

	public List<WebElement> getListBtnClearResponse() {
		return listBtnClearResponse;
	}

	@FindBy(css = "div.test-action-item>button")
	private List<WebElement> listBtnSubmit;

	public List<WebElement> getListBtnSubmit() {
		return listBtnSubmit;
	}

	@FindBy(css = ".tcq-status-list>div.green")
	private List<WebElement> listQuestionStatusAnsweredStatus;

	public List<WebElement> getListQuestionStatusAnsweredStatus() {
		return listQuestionStatusAnsweredStatus;
	}

	public List<WebElement> getListQuestionStatusNotAnsweredStatus() {
		return listQuestionStatusNotAnsweredStatus;
	}

	public List<WebElement> getListQuestionStatusNotVisitedStatus() {
		return listQuestionStatusNotVisitedStatus;
	}

	public List<WebElement> getListQuestionStatusMarkForReviewStatus() {
		return listQuestionStatusMarkForReviewStatus;
	}

	public List<WebElement> getListQuestionStatusAnsweredAndMarkForReviewStatus() {
		return listQuestionStatusAnsweredAndMarkForReviewStatus;
	}

	@FindBy(css = ".tcq-status-list>div.red")
	private List<WebElement> listQuestionStatusNotAnsweredStatus;

	@FindBy(css = ".tcq-status-list>div.grey")
	private List<WebElement> listQuestionStatusNotVisitedStatus;

	@FindBy(css = ".tcq-status-list>div.purple")
	private List<WebElement> listQuestionStatusMarkForReviewStatus;

	@FindBy(css = ".tcq-status-list>div.purple-green")
	private List<WebElement> listQuestionStatusAnsweredAndMarkForReviewStatus;

	@FindBy(css = ".tcq-status-list>div")
	private List<WebElement> listNoOfQuestionPerSection;

	public List<WebElement> getListNoOfQuestionPerSection() {
		return listNoOfQuestionPerSection;
	}

	@FindBy(css = ".time-status>span")
	private List<WebElement> listTimeAndUserInfo;

	public List<WebElement> getListTimeAndUserInfo() {
		return listTimeAndUserInfo;
	}
	
	
	@FindBy(css = ".test-response-details>div:nth-child(2)>table>tbody>tr")
	private List<WebElement> listViewResultData;

	public List<WebElement> getListViewResultData() {
		return listViewResultData;
	}
}
