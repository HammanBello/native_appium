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

import com.appium.reports.ExtentLogger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

public class LoginPage extends BasePage {

	// @iOSXCUITFindBy(id = "test-Username") -> This id is actually accessibility ID
	// only (We can get it from Appium Inspector)
	@AndroidFindBy(id = "com.carrefour.fid.android:id/email_text")
	private MobileElement txtFldUsername;
	private String txtFldUsernameTxt = "Mon Courriel";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/dismiss")
	private MobileElement dismissButton;
	private String dismissButtonTxt = "Modifier";


	@AndroidFindBy(accessibility = "Mon Compte")
	private MobileElement accountIcon;
	private String accountIconTxt = "Icone de compte";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/button_account_home_anonymous_login")
	private MobileElement connectBtn;
	private String connectBtnTxt = "Boutton du formulaire de connection";

	@AndroidFindBy(id = "android:id/button2")
	@iOSXCUITFindBy(id = "android:id/button2")
	private MobileElement modiferButton;
	private String modiferButtonTxt = "Modifier";

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLay" +
			"out/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/andr" +
			"oid.view.View/android.view.View[3]/android.widget.Button")

	private MobileElement firstValidator;
	private String firstValidatorTxt = "Me connecter";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/btn_accept_cookies")
	@iOSXCUITFindBy(id = "com.carrefour.fid.android:id/btn_accept_cookies")
	private MobileElement acceptCookies;
	private String acceptCookiesTxt = "Accepter les cookies";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/pwd_text")
	@iOSXCUITFindBy(id = "test-Password")
	private MobileElement txtFldPassword;
	private String txtFldPasswordTxt = "Password Textbox";

	@AndroidFindBy(id = "com.carrefour.fid.android:id/connect_button")
	@iOSXCUITFindBy(id = "test-LOGIN")
	private MobileElement btnLogin;
	private String btnLoginTxt = "Login button";

	@AndroidFindBy(id = "android:id/message")
	// @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Error
	// message\"]/child::XCUIElementTypeStaticText")
	@iOSXCUITFindBy(id = "Username and password do not match any user in this service.")
	private MobileElement msgErrorTxt;

	public LoginPage acceptCookies() {

		click(acceptCookies, acceptCookiesTxt);
		return this;
	}
	public LoginPage accountIconClick() {
		try {
			int attempts = 0;
			while(attempts < 2) {
				try {
					click(accountIcon, accountIconTxt);
					break;
				} catch(org.openqa.selenium.StaleElementReferenceException e) {
				}
				attempts++;
			}
		}

		catch (NoSuchElementException | TimeoutException exception){
			ExtentLogger.fail("L'icone du compte n'est pas visible");
			Assert.fail("L'icone du compte n'est pas visible");

		}

		return this;
	}

	public LoginPage connectBtnClick() {
		try {
		click(connectBtn, connectBtnTxt);		}

		catch (NoSuchElementException | TimeoutException exception){
		ExtentLogger.fail("Le boutton de connexion n'est pas visible");
			Assert.fail("Le boutton de connexion n'est pas visible");

	}
		return this;
	}

	public LoginPage firstValidatorClick() {
		click(firstValidator, firstValidatorTxt);
		return this;
	}

	public LoginPage dismissBtnClick() {
		waitForVisibility(dismissButton,6);
		waitForVisibility(dismissButton);
		click(dismissButton, dismissButtonTxt);
		return this;
	}
	public LoginPage enterUsername(String userName) {
try{
		sendKeys(txtFldUsername, userName, txtFldUsernameTxt);}
catch (NoSuchElementException | TimeoutException exception){
		ExtentLogger.fail("Le champ de saisie du courriel n'est pas visible");
	Assert.fail("Le champ de saisie du courriel n'est pas visible");

}
		return this;
	}

	public LoginPage enterPassword(String password) {
		// sendKeys(txtFldPassword, password);
		try {
		sendKeys(txtFldPassword, password, txtFldPasswordTxt);
	}
catch (NoSuchElementException | TimeoutException exception){
		ExtentLogger.fail("Le champ de saisie du mot de passe n'est pas visible");
	Assert.fail("Le champ de saisie du mot de passe n'est pas visible");

	}
		return this;
	}

	public boolean isModifierDisplayed() {


		return isDisplayedMod(modiferButton,3);
	}

	public ProductsPage pressLoginBtn() {
		// click(btnLogin);
		try {
		click(btnLogin, btnLoginTxt);
	}
catch (NoSuchElementException | TimeoutException exception){
		ExtentLogger.fail("Le boutton de connexion n'est pas visible");
	Assert.fail("Le boutton de connexion n'est pas visible");

	}
		return new ProductsPage();
	}

	public LoginPage pressModifierBtn() {
		// click(btnLogin);
		try {
		click(modiferButton, modiferButtonTxt);
	}
catch (NoSuchElementException | TimeoutException exception){
		ExtentLogger.fail("Le boutton pour pouvoir a nouveau modifier le formulaire n'est pas visible");
		Assert.fail("Le boutton pour pouvoir a nouveau modifier le formulaire n'est pas visible");
	}
		return this;
	}

	public ProductsPage login(String username, String password) {
		enterUsername(username).enterPassword(password).pressLoginBtn();
		return new ProductsPage();
	}

	public String getErrorTxt() {
		// return getAttribute(msgErrorTxt, TEXT);
		try {return getElementText(msgErrorTxt);
	}
catch (NoSuchElementException | TimeoutException exception){
		ExtentLogger.fail("Le message d'erreur de la connexion n'est pas visible");
		return "";
	}

	}

}
