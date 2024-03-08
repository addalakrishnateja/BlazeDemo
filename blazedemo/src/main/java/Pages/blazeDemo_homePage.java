package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class blazeDemo_homePage {
	
	
	@FindBy(name = "fromPort") public WebElement departureCity;
	@FindBy(name="toPort")     public WebElement destinationCity;
	@FindBy(tagName = "input") public WebElement findFlightsButton;
	@FindBy(xpath = "//input[@value='Purchase Flight']") public WebElement purchaseFlight;
	
	public blazeDemo_homePage(WebDriver w) {
		PageFactory.initElements(w, this);
	}

}
