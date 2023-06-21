package com.mohs10.scripts;

import org.testng.annotations.Test;

import com.mohs10.ActionDriver.SSreuseclass;
import com.mohs10.ActionDriver.XLUtils;
import com.mohs10.base.StartBrowser;

import com.mohs10.reuse.CommonFuns;

public class HomeTest extends StartBrowser {

	String excelfile = "TestData\\Data.xlsx";
	String excelsheet = "Login";

	@Test
	public void chrome() throws Exception {

		CommonFuns coms = new CommonFuns();
		int rowcount = XLUtils.getRowCount(excelfile, excelsheet);
		for (int i = 1; i < rowcount; i++) {
			String Email = XLUtils.getStringCellData(excelfile, excelsheet, i, 0);
			String Pwd = XLUtils.getStringCellData(excelfile, excelsheet, i, 1);
			String url = XLUtils.getStringCellData(excelfile, excelsheet, i, 2);
			String browser = XLUtils.getStringCellData(excelfile, excelsheet, i, 3);

			driver = StartBrowser.beforeClass(browser, url);
            System.out.println(browser + " :this is the opened browser");
			Thread.sleep(2000);
			coms.logIn(Email, Pwd);
            coms.Categories();
			coms.Blogbtn();
			coms.Newsbtn();
			// coms.logout();
			System.out.println(browser + " :current browser closed");
			Thread.sleep(2000);

			SSreuseclass.SSReusablemethod(driver, "Screenshoot:"+i);
			driver.close();

		}
	}
}
