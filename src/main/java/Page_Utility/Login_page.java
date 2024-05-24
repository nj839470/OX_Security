package Page_Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common_Utility.Base_Utility;

public class Login_page extends Base_Utility {
	public Login_page() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text() ='Login']")
	private WebElement login_button;

	public WebElement login_button() {
		return login_button;
	}

	@FindBy(xpath = "//input[@id='username']")
	private WebElement user_field;

	public WebElement user_field() {
		return user_field;
	}

	@FindBy(xpath = "//input[@type='password']")
	private WebElement pass_field;

	public WebElement pass_field() {
		return pass_field;
	}

	@FindBy(xpath = "//button[text() ='Continue']")
	private WebElement continue_button;

	public WebElement continue_button() {
		return continue_button;
	}
}
