package TestScripts;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Base.Driver;
import Pages.blazeDemo_homePage;

public class blazeDemoVacation extends Driver{

	blazeDemo_homePage blazepage;
	@Test
	public void testBlazeDemoVacation() throws Exception{
		w.get("https://blazedemo.com/index.php");
		String Parentwindow = w.getWindowHandle();
		Thread.sleep(2000);
		assertEquals(w.findElement(By.tagName("h1")).getText(), "Welcome to the Simple Travel Agency!");

		WebElement hyperLink = w.findElement(By.xpath("(//a)[4]"));
		WebDriverWait ww = new WebDriverWait(w, Duration.ofSeconds(20));
		ww.until(ExpectedConditions.elementToBeClickable(hyperLink));
		hyperLink.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		Thread.sleep(2000);
		Set<String> handles = w.getWindowHandles();

		Iterator<String> I = handles.iterator();
		while(I.hasNext()) {
			String childwindow = I.next();
			if(!Parentwindow.equals(childwindow)) {
				w.switchTo().window(childwindow);
				Thread.sleep(2000);
				assertEquals(w.getTitle(), "BlazeDemo - vacation");
			}
		}

		w.switchTo().window(Parentwindow);
		blazepage = new blazeDemo_homePage(w);
		Select departure = new Select(blazepage.departureCity);
		departure.selectByValue("Mexico City");

		Select destination = new Select(blazepage.destinationCity);
		destination.selectByValue("London");

		blazepage.findFlightsButton.click();
		Thread.sleep(2000);

		List<WebElement> ll = w.findElements(By.xpath("//tbody/tr/td[6]"));
		ArrayList<String> al = new ArrayList();
		for(int i = 0;i<ll.size();i++) {
			al.add(ll.get(i).getText());
		}

		String st = Collections.min(al);
		int count = 0;
		List<WebElement> tr_collection = w.findElements(By.xpath("//tbody/tr"));
		for(WebElement tr : tr_collection) {
			List<WebElement> td_collection = w.findElements(By.xpath("//tbody/tr/td"));
			for( int i=0;i<td_collection.size();i++) {
				WebDriverWait wait = new WebDriverWait(w, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(td_collection.get(i)));
				if(td_collection.get(i).getText().equals(st)) {
					String xpath = "(//tbody/tr/td)[%d]";
					String res = String.format(xpath,i-4);
					w.findElement(By.xpath(res)).click();
					Thread.sleep(2000);break;
				}
			}
		}
		if(w.findElement(By.xpath("(//div[@class='container'])[2]/p[5]")).isDisplayed()) {
			JavascriptExecutor js =  (JavascriptExecutor)w;
			js.executeScript("arguments[0].scrollIntoView(true);", blazepage.purchaseFlight);
		}
		
		blazepage.purchaseFlight.click();
		Thread.sleep(2000);
		assertEquals(w.findElement(By.tagName("h1")).getText(), "Thank you for your purchase today!");
		System.out.println("Store Id value : "+ w.findElement(By.xpath("(//tbody/tr)[1]/td[2]")).getText());



	}
}
