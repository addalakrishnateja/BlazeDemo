package Base;

import java.time.Duration;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Driver {

	
	public static WebDriver w;
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		
		switch (browser) {
		case "chrome":
			w = new ChromeDriver();
			break;
		case "firefox":
			w = new FirefoxDriver();
			break;
		case "IE":
			w = new InternetExplorerDriver();
			break;

		default:
			w = new ChromeDriver();
			break;
		}
		
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		w.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		
	}
	
	@AfterMethod
	public void tearDown() {
		w.quit();
	}
}
