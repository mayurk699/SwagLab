package LibraryFiles;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass
{
	public WebDriver driver;
	public void initializeBrowser() throws IOException
	{

	    ChromeOptions options = new ChromeOptions();

	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    prefs.put("profile.password_manager_leak_detection", false);

	    options.setExperimentalOption("prefs", prefs);

	    options.addArguments(
	        "--disable-features=PasswordManagerEnableCheck,PasswordLeakDetection,PasswordProtectionWarningTrigger"
	    );

	    options.addArguments("--disable-notifications");
	    options.addArguments("--disable-infobars");

	    // 🔥 THIS IS CRITICAL
	    options.addArguments(
	        "--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/selenium-profile"
	    );

		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(UtilityClass.getTestDataFromPropertyFiles("URL"));
		
	}

}
