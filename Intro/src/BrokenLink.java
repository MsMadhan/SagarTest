import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class BrokenLink {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		// for single link
		/*String url=driver.findElement(By.cssSelector("a[href*='brokenlink']")).getAttribute("href");
		HttpURLConnection conn =(HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		int resp=conn.getResponseCode();
		System.out.println(resp);*/
		
		//for all links
		SoftAssert a =new SoftAssert();
		List <WebElement> links =driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		for(WebElement link: links)
		{
			String url=link.getAttribute("href");
			HttpURLConnection conn =(HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int resp=conn.getResponseCode();
			System.out.println(link.getText()+"response code"+resp);
			a.assertTrue(resp<400, "the broken link name is"+link.getText()+"with response code"+resp);
			
//			if (resp>=400) {
//				System.out.println("the broken link name is"+link.getText()+"with response code"+resp);
//				Assert.assertTrue(false);
//			}
			
		}
		a.assertAll();
		
	}

}
