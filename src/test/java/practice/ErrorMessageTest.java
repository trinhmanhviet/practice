package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ErrorMessageTest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public SetUpDriver setup = new SetUpDriver();
	
	public String username = "rahul";
	public String rightPass = null;
	
	@BeforeTest
	public void initial() {
		driver = setup.driverConfig();
		wait = setup.explicitwait();
	}
	
	@Test(priority=1)
	public void errMsg()
	{		
		System.out.println("-----");
		
		driver.get("https://www.rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.xpath("//input[@id='inputUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='inputPassword']")).sendKeys("rahul123");
		driver.findElement(By.xpath("//input[@id='chkboxOne']")).click();
		driver.findElement(By.xpath("//button[@class='submit signInBtn']")).click();
		System.out.println(driver.findElement(By.xpath("//p[@class='error']")).getText());
		
	}
	
	@Test(priority=2)
	public void resetPass()
	{
		
		System.out.println("-----");
		
		driver.findElement(By.linkText("Forgot your password?")).click();
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("rahul");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("rahulshetty@gmail.com");
		WebElement resetBtn = driver.findElement(By.xpath("//button[@class='reset-pwd-btn']"));
		wait.until(ExpectedConditions.visibilityOf(resetBtn));
		resetBtn.click();
		String passttxt = driver.findElement(By.xpath("//p[@class='infoMsg']")).getText();
		String[] a = passttxt.split("'");
		rightPass = a[1];
	}

	@Test(dependsOnMethods = "resetPass")
	public void loginWithRightPass() throws InterruptedException
	{
		WebElement goToLoginBtn = driver.findElement(By.xpath("//button[@class='go-to-login-btn']"));
		WebElement rmbID = driver.findElement(By.xpath("//input[@id='chkboxOne']"));
		WebElement loginBtn = driver.findElement(By.xpath("//button[@class='submit signInBtn']"));
		wait.until(ExpectedConditions.visibilityOf(goToLoginBtn));
		goToLoginBtn.click();
		//Thread.sleep(200);
		driver.findElement(By.xpath("//input[@id='inputUsername']")).sendKeys("rahul");
		driver.findElement(By.xpath("//input[@name='inputPassword']")).sendKeys(rightPass);
		wait.until(ExpectedConditions.elementToBeClickable(rmbID));
		if(!rmbID.isSelected())
		{
			rmbID.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}
		
}
