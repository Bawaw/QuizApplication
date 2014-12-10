import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			FileInputStream file = new FileInputStream(new File(
					"vragen-uit-excel.xlsx"));
			Workbook wb = new XSSFWorkbook(file);
			Sheet sheet = wb.getSheetAt(0);
	
			 for (int i = 0; i <= sheet.getLastRowNum(); i++) {
	                Row row = sheet.getRow(i);
	                int colCounts = row.getLastCellNum();
	                System.out.println("Total Number of Cols: " + colCounts);
	                for (int j = 0; j < colCounts; j++) {
	                    Cell cell = row.getCell(j);
	                    System.out.println("[" + i + "," + j + "]=" + cell.getStringCellValue());
	                }
	            }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
