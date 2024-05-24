package Page_object_test;

import org.testng.annotations.Test;

import Common_Utility.Base_Utility;
import Page_Utility.Issues_info;

public class Issues_info_test extends Base_Utility {

	public Issues_info ob;

	@Test(priority = 0)
	public void TC002_navigate_to_issue_info() throws InterruptedException {
		ob = new Issues_info();
		Thread.sleep(15000);
		mouseOver(ob.issue_button(), "Issues ");
		Custom_click(ob.Active_Issues(), "Active issue");
		Custom_click(ob.Severity(), " Severity ");
		mouseOver(ob.high_checkbox(), "High checkbox ");
		Custom_click(ob.high_checkbox(), "High checkbox ");
		msg(ob.active_high_issue(), " Total Active high issue =");
	}

}
