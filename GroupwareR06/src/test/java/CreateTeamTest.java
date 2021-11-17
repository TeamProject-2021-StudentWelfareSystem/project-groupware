import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CreateTeamTest {
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
	public void createTeam() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("submitId")).click();
		
		driver.findElement(By.linkText("팀")).click();
	    driver.findElement(By.linkText("팀 생성")).click();
	    driver.findElement(By.id("lectureName")).click();
	    driver.findElement(By.id("lectureName")).sendKeys("글로벌문화");
	    driver.findElement(By.id("search")).click();
	    driver.findElement(By.id("lecture")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("lecture"));
	      dropdown.findElement(By.xpath("//option[. = '글로벌문화 김민주']")).click();
	    }
	    driver.findElement(By.id("teamName")).click();
	    driver.findElement(By.id("teamName")).sendKeys("test");
	    driver.findElement(By.id("studentID")).click();
	    driver.findElement(By.id("studentID")).sendKeys("60161617");
	    driver.findElement(By.id("studentName")).click();
	    driver.findElement(By.id("studentName")).sendKeys("이수빈");
	    driver.findElement(By.id("createButton")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();
	    driver.findElement(By.linkText("글로벌문화")).click();
	}
}
