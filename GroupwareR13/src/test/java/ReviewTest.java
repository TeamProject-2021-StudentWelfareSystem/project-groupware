import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ReviewTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		//driver.quit();
	}

	@Test
	public void review() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("submitId")).click();
		
		driver.findElement(By.linkText("팀")).click();
	    driver.findElement(By.linkText("후기 작성")).click();
	    driver.findElement(By.id("team")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("team"));
	      dropdown.findElement(By.xpath("//option[. = 'test 글로벌문화 김민주']")).click();
	    }
	    driver.findElement(By.cssSelector(".fa-sm")).click();
	    driver.findElement(By.id("teamUser")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("teamUser"));
	      dropdown.findElement(By.xpath("//option[. = '이수빈 60161617']")).click();
	    }
	    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4)")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4) > .custom-control")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4) .custom-control-label")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4) .custom-control-label")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(4) .custom-control-label")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(4) .custom-control-label")).click();
	    driver.findElement(By.id("saveButton")).click();
	    driver.switchTo().alert().accept();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	@Test
	public void search() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("submitId")).click();
		
	    driver.findElement(By.linkText("사용자 검색")).click();
	    driver.findElement(By.id("searchKeyWord")).click();
	    driver.findElement(By.id("searchKeyWord")).sendKeys("이수빈");
	    driver.findElement(By.cssSelector(".fa-sm")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".fa-sm"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.linkText("이수빈")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
}
