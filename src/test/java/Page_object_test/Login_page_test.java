package Page_object_test;

import org.testng.annotations.Test;

import Common_Utility.Base_Utility;
import Page_Utility.Login_page;

public class Login_page_test extends Base_Utility {
	
	public Login_page ob;
	@Test(priority = 0)
	public void TC001_Ligon_with_Valid_Credentials() {
		ob = new Login_page();
		Custom_click(ob.login_button(), "Login button");
		custom_sendkeys(ob.user_field(), config_getdata("user_id"), "User id");
		Custom_click(ob.continue_button(), "Continue button");
		custom_sendkeys(ob.pass_field(), config_getdata("pass"), "Passward");
		Custom_click(ob.continue_button(), "Continue");
	}
}
