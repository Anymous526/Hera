package com.justinmobile.bmp.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

@Service("excelUtils")
public class ExcelUtils {
	
	public static int MAX_ROWS_NUM = 100000;

	private static final String FILE_SUFFIX = ".xls";
	
	private static final String HEADERS_SEPORTER = "|";
	
	@SuppressWarnings("deprecation")
	public String toExcel(List<List<String>> list, String[] header, String[] fileCondition, String filePath) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();

		int rowIndex = 0;

		if (!ArrayUtils.isEmpty(fileCondition)) {
			HSSFRow row = sheet.createRow(rowIndex);
			writeFileCondition(null,row, fileCondition);
			rowIndex++;

		}
		if (header.length != 0) {
			HSSFRow row = sheet.createRow(rowIndex);
			writeHeader(header, row);
			rowIndex++;
		}

		for (int i = 0; i < list.size(); i++) {
			List<String> colList = (List<String>) list.get(i);
			HSSFRow row = sheet.createRow(rowIndex+i);
			for (short colIndex = 0; colIndex < colList.size(); colIndex++) {
				HSSFCell cell = row.createCell(colIndex);
				if (colList.get(colIndex) == null) {
					continue;
				}
				cell.setCellValue(new HSSFRichTextString(colList.get(colIndex).toString()));
				// HSSFCellStyle.
				// cell.setCellType(cellType);
			}
//			rowIndex++;
		}
		String fileName = getFileName();		
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		wb.write(out);
		out.close();
		return fileName;
	}

	@SuppressWarnings("deprecation")
	private void writeHeader(String[] headers, HSSFRow row) {
		for (short colnum = 0; colnum < headers.length; colnum++) {
			String[] hd = headers[colnum].split("\\"+HEADERS_SEPORTER);
			HSSFCell cell = row.createCell(colnum);		    
			cell.setCellValue(new HSSFRichTextString(hd[0]));
		}
	}
	
	@SuppressWarnings("deprecation")
	private void writeFileCondition(String[] headers, HSSFRow row,String[] fileCondition) {
		for (short colnum = 0; colnum < fileCondition.length; colnum++) {			
			HSSFCell cell = row.createCell(colnum);	
			cell.setCellValue(new HSSFRichTextString(fileCondition[colnum]));
		}
	}

	private String getFileName() {
		StringBuffer fileName = new StringBuffer();
		fileName.append(CalendarUtils.fomatCalendar(Calendar.getInstance(), CalendarUtils.LONG_FORMAT));
		fileName.append(FILE_SUFFIX);
		return fileName.toString();
	}
	
	@SuppressWarnings("deprecation")
	public void writeToOutputStream(List<List<String>> list, String[] header, String[] fileCondition, OutputStream outputStream) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();

		int rowIndex = 0;

		if (!ArrayUtils.isEmpty(fileCondition)) {
			HSSFRow row = sheet.createRow(rowIndex);
			writeFileCondition(null,row, fileCondition);
			rowIndex++;

		}
		if (header.length != 0) {
			HSSFRow row = sheet.createRow(rowIndex);
			writeHeader(header, row);
			rowIndex++;
		}

		for (int i = 0; i < list.size(); i++) {
			List<String> colList = (List<String>) list.get(i);
			HSSFRow row = sheet.createRow(rowIndex+i);
			for (short colIndex = 0; colIndex < colList.size(); colIndex++) {
				HSSFCell cell = row.createCell(colIndex);
				if (colList.get(colIndex) == null) {
					continue;
				}
				cell.setCellValue(new HSSFRichTextString(colList.get(colIndex).toString()));
			}
		}
		wb.write(outputStream);
	}

}
