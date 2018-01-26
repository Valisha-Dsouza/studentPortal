package com.cdac.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static List read(InputStream inputStream) {
		List<Row> rows = new ArrayList<Row>();
		try {
			ClassLoader classLoader = ExcelReader.class.getClassLoader();
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			if(iterator.hasNext()){
				iterator.next();
			}
			while (iterator.hasNext()) {
				rows.add(iterator.next());
				
			}

			workbook.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
}
