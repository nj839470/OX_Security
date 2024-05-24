package Page_Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common_Utility.Base_Utility;

public class Issues_info extends Base_Utility {

	public Issues_info() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='/issues']//button")
	private WebElement issue_button;

	public WebElement issue_button() {
		return issue_button;
	}

	@FindBy(xpath = "//p[normalize-space()='Active Issues']")
	private WebElement Active_Issues;

	public WebElement Active_Issues() {
		return Active_Issues;
	}

	@FindBy(xpath = "//div[@data-testid='filter_box filter_header_Severity']")
	private WebElement Severity;

	public WebElement Severity() {
		return Severity;
	}

	@FindBy(xpath = "((//div[@aria-label='High'])[2]//span)[1]")
	private WebElement high_checkbox;

	public WebElement high_checkbox() {
		return high_checkbox;
	}

	@FindBy(xpath = "//span[@class='css-2dl6fu-count']")
	private WebElement active_high_issue;

	public WebElement active_high_issue() {
		return active_high_issue;
	}
}
