package database;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DBReadHandler {

	public ExcelReader() {
	}

	@Override
	public void read(String path, DBDataHandler dataHandler) throws DBException {
		ArrayList<Object> retList = new ArrayList<Object>();
		try {
			FileInputStream file = new FileInputStream(path);
			Workbook wb = new XSSFWorkbook(file);
			Sheet sheet = wb.getSheetAt(0);

			int colCounts = sheet.getRow(0).getLastCellNum();

			// Start after legend
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCounts; j++) {
					Cell c = row.getCell(j);
					if (c != null) {
						c.setCellType(Cell.CELL_TYPE_STRING);
						retList.add(c.getStringCellValue());
					} else
						retList.add("");

				}
			}
			dataHandler.handleData(retList);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

}
