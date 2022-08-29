
// Generated by Selenium IDE
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LectureRoomReservationTest {
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
		// driver.quit();
	}

	@Test
	public void lectureRoomReservation() {
		driver.get("http://localhost:8080/groupware/home");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("로그인")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("00000000");
		driver.findElement(By.id("userLoginPW")).sendKeys("12341234");
		driver.findElement(By.id("form")).click();
		driver.findElement(By.id("submitId")).click();
		driver.findElement(By.linkText("강의실")).click();
		driver.findElement(By.linkText("강의실")).click();
		driver.findElement(By.linkText("강의실")).click();
		driver.findElement(By.linkText("강의실 예약")).click();
		driver.findElement(By.linkText("1246")).click();
		driver.findElement(By.id("reservationStartTime")).click();
		{
			WebElement dropdown = driver.findElement(By.id("reservationStartTime"));
			dropdown.findElement(By.xpath("//option[. = '09:00~11:00']")).click();
		}
		driver.findElement(By.id("reservationNumOfPeople")).click();
		driver.findElement(By.id("reservationNumOfPeople")).sendKeys("10");
		driver.findElement(By.id("saveButton")).click();
	}
}