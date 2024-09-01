package CSVFileReading;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;

public class CSVReadingFile {
	String csvpath="./CSV Data/SeleniumCSV.csv";
	WebDriver driver;
	
	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "./ChromeDriverJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://only-testing-blog.blogspot.com/2014/05/form.html");
		driver.manage().window().maximize();
	}
	@Test
	public void LocatorsTestingBlog() throws IOException, InterruptedException {
		CSVReader r=new CSVReader(new FileReader(csvpath));
		String[] csvCell;
		while((csvCell=r.readNext())!=null)
		{
			String FName=csvCell[0];
			String LName=csvCell[1];
			String Email=csvCell[2];
			String MNum=csvCell[3];
			String CName=csvCell[4];
			
			driver.findElement(By.name("FirstName")).sendKeys(FName);
			driver.findElement(By.name("LastName")).sendKeys(LName);
			driver.findElement(By.name("EmailID")).sendKeys(Email);
			driver.findElement(By.name("MobNo")).sendKeys(MNum);
			driver.findElement(By.name("Company")).sendKeys(CName);
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@id=\"post-body-8228718889842861683\"]/div[1]/form/input[6]")).click();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			
		}
		driver.quit();
	}

}
