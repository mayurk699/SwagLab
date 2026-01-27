package module1_Login_Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import module1_Login.SwagLabHomePage;
import module1_Login.SwagLabLoginPage;
import module1_Login.SwaglabMenuPage1;

public class TC1_SLHomePageVerifyTitle extends BaseClass
{	
	SwagLabLoginPage login;
	SwagLabHomePage home;
	SwaglabMenuPage1 menu;
	int TCID;
		 
	@BeforeClass
	public void openBrowser() throws EncryptedDocumentException, IOException
	{	
		 initializeBrowser();
		 
		 login=new SwagLabLoginPage(driver);
		 home=new SwagLabHomePage(driver);
		 menu = new SwaglabMenuPage1(driver);
		
	}

	@BeforeMethod
	public void loginToApplication() throws InterruptedException, EncryptedDocumentException, IOException
	{
		Thread.sleep(500);
		login.inpSwagLabLoginPageUN(UtilityClass.getTestDataFromPropertyFiles("UN"));
		Thread.sleep(500);
		login.inpSwagLabLoginPagePWD(UtilityClass.getTestDataFromPropertyFiles("PWD"));
		Thread.sleep(500);
		login.clickSwagLabLoginPageLoginBtn();
	}
	
	@Test
	public void verifyTitle() throws InterruptedException, EncryptedDocumentException, IOException
	{
		TCID=1;
		String actTitle = home.verifySwagLabHomePageTitle();
		String expTitle=UtilityClass.getTD(0, 2);
		Assert.assertEquals(actTitle, expTitle,"Failed Title Mismatch");
//		home.verifySwagLabHomePageTitle(sh.getRow(0).getCell(2).getStringCellValue());
		Thread.sleep(500);
		
		
	}
	
	@AfterMethod
	public void logoutFromApplication(ITestResult ss) throws InterruptedException, IOException
	{
		if(ss.getStatus()==ITestResult.FAILURE)
		{
			UtilityClass.captureSS(driver, TCID);
		}
		home.clickSwagLabHomePageMenuBtn();
		Thread.sleep(500);
		menu.clickSwagLabMenuPageLogoutBtn();
	
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
