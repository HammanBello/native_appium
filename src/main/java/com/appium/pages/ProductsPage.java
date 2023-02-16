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

import com.appium.manager.DriverManager;
import com.appium.reports.ExtentLogger;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

//public class ProductsPage extends MenuPage {
public class ProductsPage extends BasePage {



	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayo" +
			"ut/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Fram" +
			"eLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android." +
			"view.View/android.view.View[1]/android.widget.TextView")
	private MobileElement accountPageTitle;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[1]")
	private MobileElement accountBackBtn;
	private String accountBackBtnTxt = "Boutton retour de la page votre compte";

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.EditText")
	private MobileElement researchInputText;
	private String researchInputTextTxt = "Texte du champ de saisi de la recherche";

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")
	private MobileElement researchInput;
	private String researchInputTxt = "Texte du champ de saisi de la recherche";


	@AndroidFindBy(id = "com.carrefour.fid.android:id/titlee")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
	private MobileElement searchTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
	private MobileElement SLBPrice;

//	private MenuPage menuPage;


	public ProductsPage() {
		super();
		// Composition
//		menuPage = new MenuPage();
	}

	public ProductsPage accountBackBtnClick() {
		try {
			isDisplayedMod(accountBackBtn,15);
			click(accountBackBtn, accountBackBtnTxt);}

		catch (NoSuchElementException | TimeoutException exception){
			ExtentLogger.fail("Le boutton de retour de la page de compte n'est pas visible");
			Assert.fail("Le boutton de retour de la page de compte n'est pas visible");
		}
		return this;
	}

	public ProductsPage researchInputTextClick() {
		try {click(researchInputText, researchInputTextTxt);}

		catch (NoSuchElementException | TimeoutException exception){
			ExtentLogger.fail("La barre de recherche n'est pas visible");
			Assert.fail("La barre de recherche n'est pas visible");
		}
		return this;
	}

	public ProductsPage researchInputClick() {
		try {click(researchInput, researchInputTxt);}

		catch (NoSuchElementException | TimeoutException exception){
			ExtentLogger.fail("La barre de recherche n'est pas visible");
			Assert.fail("La barre de recherche n'est pas visible");
		}
		return this;
	}

	public ProductsPage enterProductName(String productName) {
		try{
			sendKeys(researchInput, productName,researchInputTxt );
			((AndroidDriver)DriverManager.getDriver()).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
		}
		catch (NoSuchElementException | TimeoutException exception){
			ExtentLogger.fail("La barre de recherche n'est pas saisisable");
			Assert.fail("La barre de recherche n'est pas saisisable");

		}
		return this;
	}


//	public MenuPage getMenuPage() {
//		return menuPage;
//	}

	public boolean isSearchTitleDisplayed() {
		return isDisplayedMod(searchTitle,20);
	}

	public String getTitle() {
		// return getAttribute(productTitle, "text");
		// return getAttribute(productTitle, TEXT);
		return getElementText(accountPageTitle);
	}

	public Boolean getSearchResult(MobileElement element,String searchedTxt) {

//		String xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup["+i+"]/android.view.ViewGroup/android.widget.TextView";
		try{
			isDisplayedMod(element,10);
			return getElementText(element).contains(searchedTxt);

		}
		catch (NoSuchElementException | TimeoutException exception){
			return false;

		}
	}


	public String getSLBPrice() {
//		return getAttribute(SLBPrice, TEXT);
		return getElementText(SLBPrice);

	}


}
