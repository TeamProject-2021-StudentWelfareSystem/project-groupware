import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CRUDTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		//driver.quit();
	}

	@Test
	public void testA() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("submitId")).click();
		
		driver.findElement(By.linkText("게시판")).click();
	    driver.findElement(By.linkText("커뮤니티")).click();
	    driver.findElement(By.linkText("글쓰기")).click();
	    driver.findElement(By.id("communityTitle")).click();
	    driver.findElement(By.id("communityTitle")).sendKeys("글쓰기 테스트용");
	    driver.findElement(By.cssSelector(".ck-editor__editable")).click();
	    driver.findElement(By.cssSelector(".ck-editor__editable")).sendKeys("테스트입니다");
	    driver.findElement(By.id("saveButton")).click();
	    driver.findElement(By.linkText("테스트")).click();
	}
	@Test
	public void testB() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("submitId")).click();
		
		driver.findElement(By.linkText("게시판")).click();
	    driver.findElement(By.linkText("커뮤니티")).click();
		driver.findElement(By.linkText("글쓰기 테스트용")).click();
	    driver.findElement(By.id("modifyButton")).click();
	    driver.findElement(By.id("communityTitle")).click();
	    driver.findElement(By.id("communityTitle")).clear();
	    driver.findElement(By.id("communityTitle")).sendKeys("수정 테스트");
	    driver.findElement(By.cssSelector(".ck-editor__editable")).click();
	    driver.findElement(By.cssSelector(".ck-editor__editable")).sendKeys("123123");
	    driver.findElement(By.id("listButton")).click();
	}
	@Test
	public void testC() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("submitId")).click();
		
		driver.findElement(By.linkText("게시판")).click();
	    driver.findElement(By.linkText("커뮤니티")).click();	    
	    driver.findElement(By.linkText("수정 테스트")).click();
	    driver.findElement(By.id("deleteButton")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();
	}
}
