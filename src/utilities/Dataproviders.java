package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders 
{
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
	    try {
	        String path = ".\\testdata\\sheet.xlsx";
	        Excelutility xlutil = new Excelutility(path);

	        int totalrows = xlutil.getRowcount("Sheet1");
	        int totalcols = xlutil.getcellcount("Sheet1", 1);

	        String logindata[][] = new String[totalrows][totalcols];

	        for (int i = 1; i <= totalrows; i++) {
	            for (int j = 0; j < totalcols; j++) {
	                logindata[i - 1][j] = xlutil.getcellData("Sheet1", i, j);
	            }
	        }

	        return logindata;
	    } catch (Exception e) {
	        System.out.println("Error in DataProvider: " + e.getMessage());
	        throw e;  // Important to re-throw to show actual failure
	    }
	}

}
