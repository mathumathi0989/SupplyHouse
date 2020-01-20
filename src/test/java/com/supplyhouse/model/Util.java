package com.supplyhouse.model;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class Util {
	public  static String  url = "https://www.supplyhouse.com/Boiler-Parts-Finder-Tool";
	public static int waittime = 3;
    public static String PATH = System.getProperty("user.dir");
    public static String FILE_PATH = PATH+"\\src\\test\\java\\com\\supplyhouse\\model\\testData.xls";
    public static Workbook book;
    public static org.apache.poi.ss.usermodel.Sheet sheet;
    private static FileInputStream fis = null;
	public static List<String> ExcelReader(String fileName, String sheetname) throws Exception{
		List<String> list= new ArrayList<String>();;
		try {
			fis = new FileInputStream(new File(fileName));
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
			sheet = workbook.getSheet(sheetname);
			for ( int i = 0; i<=sheet.getLastRowNum()-1; i++) {
			   String val =  sheet.getRow(i+1).getCell(0).getStringCellValue();
				   list.add(val);
			   	}
				} catch (FileNotFoundException fnfEx) {
			System.out.println(fileName + " is not Found. please check the file name.");
			System.exit(0);
		} catch (IOException ioEx) {
			System.out.println(fis + " is not Found. please check the path.");
		} catch (Exception ex) {
			System.out.println("There is error reading/loading xls file, due to " + ex);
		}
		return list;
	}
}
