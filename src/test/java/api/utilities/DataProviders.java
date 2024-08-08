package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // Get all the data from Excel sheet

    @DataProvider(name = "Data")
    public Object[][] getAllData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/userdata.xlsx";
        XLUtility xlUtil = new XLUtility(path);

        int rowCount = xlUtil.getRowCount("Sheet1");
        int cellCount = xlUtil.getCellCount("Sheet1", 1);

        String[][] apiData = new String[rowCount][cellCount];

        //DataFormatter formatter = new DataFormatter(); // create a DataFormatter instance

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < cellCount; j++) {
                apiData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j); // use the formatter to get cell value as a String
            }
        }
        return apiData;
    }

    // Get only userNames from Excel sheet
    @DataProvider(name = "UserNames")
    public Object[][] getUserNames() {
        String path = System.getProperty("user.dir") + "/src/test/resources/userdata.xlsx";
        XLUtility xlUtil = new XLUtility(path);

        int rowCount = xlUtil.getRowCount("Sheet1");

        Object[][] apiData = new Object[rowCount][1]; // change to Object[][]

        for (int i = 1; i <= rowCount; i++) {
            apiData[i - 1][0] = xlUtil.getCellData("Sheet1", i, 1); // change to apiData[i - 1][0]
        }
        return apiData;
    }


}
