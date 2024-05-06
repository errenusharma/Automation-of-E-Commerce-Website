package com.automation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test_Util {
	//utility  file for Excel reader 
	public static Map<Integer, ArrayList<String>> read_Excel(String filePath, int sheetIndex) throws IOException {
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheetAt(sheetIndex);
		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

		Iterator<Row> rowitr = sheet.iterator();
		while (rowitr.hasNext()) {
			Row row = (Row) rowitr.next();
			if (row.getRowNum() == 0) {
				continue;
			} else {
				Iterator<org.apache.poi.ss.usermodel.Cell> cellItr = row.cellIterator();
				ArrayList<String> dataList = new ArrayList<String>();
				while (cellItr.hasNext()) {
					org.apache.poi.ss.usermodel.Cell cell = cellItr.next();
					CellType cellType = cell.getCellType();
					switch (cellType) {
					case STRING:
						dataList.add(cell.getStringCellValue());
						continue;
					case NUMERIC:
						String value = new BigDecimal(cell.getNumericCellValue()).toString();
						if (value.contains(".0"))
							value = value.replace(".0", "");
						dataList.add(value.trim());
						continue;
					default:
						System.out.println("Default");
						break;
					}
				}
				map.put(row.getRowNum(), dataList);
			}
		}
		book.close();
		fis.close();
		return map;
	}
}
