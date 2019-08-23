package samplemaven;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browserstack {

	public static final String USERNAME = "jawadrana1";
	public static final String AUTOMATE_KEY = "6r8cRsmzAgbaJd7jnPsp";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	String baseUrl = "https://www.demo.iscripts.com/socialware/demo//";
	public WebDriver driver = null;

	
	@BeforeClass
	public void setup() throws MalformedURLException {


		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "76.0");
		caps.setCapability("project", "samplemaven");
		caps.setCapability("name", "Browserstack");
		caps.setCapability("browserstack.local", "false");
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("browserstack.selenium_version", "3.5.2");
		
		driver = new RemoteWebDriver(new URL(URL), caps);
	}
	
	@BeforeMethod
	public void Setup()throws Exception{
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


	}
	
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl);
		System.out.println("Browser Opened");

	}

		@Test(priority=0)
		public void VerifyHomepageTitle() {

		String expectedTitle = "Socialware";
		String actualTitle= driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority=1)
	public void UserRegistration() {
		driver.findElement(By.xpath("//*[@id=\"user_emails\"]")).sendKeys("user@22iscripts.com");
		System.out.println("Email Entered");
		driver.findElement(By.xpath("//*[@id=\"user_passwords\"]")).sendKeys("User1234");
		System.out.println("Password Entered");
		driver.findElement(By.xpath("//*[@id=\"user_confirm_password\"]")).sendKeys("User1234");
		System.out.println("Password ReEntered");
		driver.findElement(By.xpath("//*[@id=\"terms_conditions\"]")).click();
		System.out.println("Terms of Service clicked");

		driver.findElement(By.xpath("//*[@id=\"frmUserRegister\"]/input")).click(); 
		System.out.println("Sign up Clicked");
		driver.findElement(By.xpath("//*[@id=\"menu\"]/li[4]/ul/li/a/div")).click();
		driver.findElement(By.xpath("//*[@id=\"menu\"]/li[4]/ul/li/ul/li[5]/a")).click();
		System.out.println("logged out");
	}

	@Test(priority=2)
	public void Userlogin() {
		driver.findElement(By.xpath("//*[@id=\"user_email\"]")).sendKeys("user@13iscripts.com");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[2]/input")).sendKeys("User1234");
		driver.findElement(By.xpath("	//*[@id=\"frmLogin\"]/div/div[3]/input")).click();
		System.out.println("Return user loggen in");
	}
	@Test(priority=3)

	public void UserFirstPost() {
		driver.findElement(By.xpath("//*[@id=\"logoform\"]/div/div[1]/div")).sendKeys("Hello This is My First Post");
		driver.findElement(By.xpath("//*[@id=\"logoform\"]/div/div[2]/div[3]/input[2]")).click();
	}

	@AfterTest
	public void terminateBrowser(){
		driver.close();
		//driver.quit();
		System.out.println("Browser Closed");
	}
}




/**  WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    driver.get("http://www.google.com");
	    WebElement element = driver.findElement(By.name("q"));

	    element.sendKeys("BrowserStack");
	    element.submit();

	    System.out.println(driver.getTitle());
	    driver.quit();
 **/

