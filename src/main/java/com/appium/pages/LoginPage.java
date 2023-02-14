/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Appium Mobile Automation - Android & iOS + Frameworks + CICD (https://www.udemy.com/course/the-complete-appium-course-for-ios-and-android/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package com.appium.pages;

import com.appium.base.BasePage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BasePage {

	// @iOSXCUITFindBy(id = "test-Username") -> This id is actually accessibility ID
	// only (We can get it from Appium Inspector)
	@AndroidFindBy(id = "com.carrefour.fid.android:id/email_text")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement txtFldUsername;
	private String txtFldUsernameTxt = "Username Textbox";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/pwd_text")
	@iOSXCUITFindBy(id = "test-Password")
	private MobileElement txtFldPassword;
	private String txtFldPasswordTxt = "Password Textbox";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/connect_button")
	@iOSXCUITFindBy(id = "test-LOGIN")
	private MobileElement btnLogin;
	private String btnLoginTxt = "Login button";

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	// @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Error
	// message\"]/child::XCUIElementTypeStaticText")
	@iOSXCUITFindBy(id = "Username and password do not match any user in this service.")
	private MobileElement msgErrorTxt;

	public LoginPage enterUsername(String userName) {
		// txtFldUsername.sendKeys(userName);
		// sendKeys(txtFldUsername, userName);
		sendKeys(txtFldUsername, userName, txtFldUsernameTxt);
		return this;
	}

	public LoginPage enterPassword(String password) {
		// sendKeys(txtFldPassword, password);
		sendKeys(txtFldPassword, password, txtFldPasswordTxt);
		return this;
	}

	public ProductsPage pressLoginBtn() {
		// click(btnLogin);
		click(btnLogin, btnLoginTxt);
		return new ProductsPage();
	}

	public ProductsPage login(String username, String password) {
		enterUsername(username).enterPassword(password).pressLoginBtn();
		return new ProductsPage();
	}

	public String getErrorTxt() {
		// return getAttribute(msgErrorTxt, TEXT);
		return getElementText(msgErrorTxt);
	}

}
