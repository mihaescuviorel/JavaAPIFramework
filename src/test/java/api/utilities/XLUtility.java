package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

// Create XLUtility class to read data from Excel
public class XLUtility {
    public FileInputStream fi;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public FileOutputStream fo;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    // Create a constructor
    public XLUtility(String path) {
        this.path = path;
        try {
            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get row count
    public int getRowCount(String sheetName) {
        sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        return rowCount;
    }

    // Get cell count
    public int getCellCount(String sheetName, int rownum) {
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum();
        return cellCount;
    }

    // Get cell data
    public String getCellData(String sheetName, int rownum, int colnum) {
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        String data = cell.toString();
        return data;
    }

    // Get cell


    // Set cell data
    public void setCellData(String sheetName, int rownum, int colnum, String data) {
        try {
            sheet = wb.getSheet(sheetName);
            row = sheet.getRow(rownum);
            cell = row.createCell(colnum);
            cell.setCellValue(data);
            fo = new FileOutputStream(path);
            wb.write(fo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close Excel
    public void closeExcel() {
        try {
            wb.close();
            fi.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
