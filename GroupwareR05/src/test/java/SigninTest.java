
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SigninTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void signin() {
		driver.get("http://localhost:8080/groupware/");
		driver.manage().window().setSize(new Dimension(1463, 864));
		driver.findElement(By.id("s_up")).click();
		driver.findElement(By.cssSelector(".checkAllText")).click();
		driver.findElement(By.cssSelector(".p-5")).click();
		driver.findElement(By.id("btnAgree")).click();
		driver.findElement(By.id("userEmail")).click();
		driver.findElement(By.id("userEmail")).sendKeys("oiuny");
		driver.findElement(By.id("emailCheck")).click();
		driver.findElement(By.id("emailNum")).click();
		driver.findElement(By.id("emailNum")).sendKeys("857643");
		driver.findElement(By.id("emailValid")).click();
		driver.findElement(By.linkText("이메일 인증 완료")).click();
		driver.findElement(By.id("userLoginID")).click();
		driver.findElement(By.id("userLoginID")).sendKeys("60185555");
		driver.findElement(By.id("idCheck")).click();
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("장문복");
		driver.findElement(By.id("userLoginPwd")).click();
		driver.findElement(By.id("userLoginPwd")).sendKeys("gkb13902");
		driver.findElement(By.id("userLoginPwdCheck")).click();
		driver.findElement(By.id("userLoginPwdCheck")).sendKeys("gkb13902");
		driver.findElement(By.id("userPhoneNum")).click();
		driver.findElement(By.id("userPhoneNum")).sendKeys("12345678");
		driver.findElement(By.id("studentGender")).click();
		driver.findElement(By.id("form")).click();
		driver.findElement(By.id("studentGender")).click();
		{
			WebElement dropdown = driver.findElement(By.id("studentGender"));
			dropdown.findElement(By.xpath("//option[. = '여']")).click();
		}
		driver.findElement(By.id("studentGrade")).click();
		{
			WebElement dropdown = driver.findElement(By.id("studentGrade"));
			dropdown.findElement(By.xpath("//option[. = '3학년']")).click();
		}
		driver.findElement(By.id("studentMajor")).click();
		driver.findElement(By.id("studentColleges")).click();
		{
			WebElement dropdown = driver.findElement(By.id("studentColleges"));
			dropdown.findElement(By.xpath("//option[. = '경영대학']")).click();
		}
		driver.findElement(By.id("studentMajor")).click();
		{
			WebElement dropdown = driver.findElement(By.id("studentMajor"));
			dropdown.findElement(By.xpath("//option[. = '국제통상학과']")).click();
		}
		driver.findElement(By.id("memberSingleMajor")).click();
		driver.findElement(By.id("SignupComplete")).click();
		driver.findElement(By.id("submitId")).click();
	}
}
