/**
 * @(#)ExcelUtils.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * description
 * 
 * @author jianguo.xu
 * @version 1.0,2011-8-5
 */
public class ExcelParser {
	 
	/**
	 * EXCEL表格
	 * 
	 * @author tiger
	 * 
	 */
	public static class Excel {
		private List<String> header;
		private List<List<Serializable>> body;

		private Excel() {
			super();
		}

		private Excel(List<String> header, List<List<Serializable>> body) {
			super();
			this.header = header;
			this.body = body;
		}

		public List<String> getHeader() {
			return header;
		}

		public List<List<Serializable>> getBody() {
			return body;
		}

		public void setHeader(List<String> header) {
			this.header = header;
		}

		public void setBody(List<List<Serializable>> body) {
			this.body = body;
		}

	}

	/**
	 * EXCEL列类型
	 * 
	 * @author tiger
	 * 
	 */
	public static enum ColumnType {
		STRING("java.lang.String"), INT("int"), INT_WRAPER("java.lang.Integer"), LONG(
				"long"), LONG_WRAPER("java.lang.Long"), DOUBLE("double"), DOUBLE_WRAPER(
				"java.lang.Double"), BOOLEAN("boolean"), BOOLEAN_WRAPER(
				"java.lang.Boolean"), BIG_DECIMAL("java.math.BigDecimal"), DATE_TIME(
				"java.util.Date"),DATE(
				"java.sql.Date");
		private final String type;

		private ColumnType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

	}

	/**
	 * EXCEL配置
	 * 
	 * @author tiger
	 * 
	 */
	public static class ExcelConfig {
		/**
		 * 文件最大字节数
		 */
		private int maxFileSize = 2048;
		/**
		 * 最大列数
		 */
		private int maxColumnCount = 200;
		/**
		 * 最大行数
		 */
		private int maxRowCount = 5000;
		/**
		 * java.sql.Date 日期格式
		 */
		private String dateFormat = "yyyy/MM/dd";
		/**
		 * java.util.Date 日期格式
		 */
		private String dateTimeFormat = "yyyy/MM/dd hh:mm:ss";
		public int getMaxFileSize() {
			return maxFileSize;
		}
		public void setMaxFileSize(int maxFileSize) {
			this.maxFileSize = maxFileSize;
		}
		public int getMaxColumnCount() {
			return maxColumnCount;
		}
		public void setMaxColumnCount(int maxColumnCount) {
			this.maxColumnCount = maxColumnCount;
		}
		public int getMaxRowCount() {
			return maxRowCount;
		}
		public void setMaxRowCount(int maxRowCount) {
			this.maxRowCount = maxRowCount;
		}
		public String getDateFormat() {
			return dateFormat;
		}
		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}
		public String getDateTimeFormat() {
			return dateTimeFormat;
		}
		public void setDateTimeFormat(String dateTimeFormat) {
			this.dateTimeFormat = dateTimeFormat;
		}
		
	}

	private ExcelConfig excelConfig;

	private ExcelParser(ExcelConfig excelConfig) {
		this.excelConfig = excelConfig;
	}

	public static ExcelParser newInstance() {
		ExcelConfig excelConfig = new ExcelConfig();
		return new ExcelParser(excelConfig);
	}

	public static ExcelParser newInstance(ExcelConfig excelConfig) {
		return new ExcelParser(excelConfig);
	}
	/**
	 * 通过文件解析EXCEL
	 * @author jianguo.xu
	 * @param file
	 * @return
	 */
	public Excel parse(File file) {
		return parse(file, null);
	}
	/**
	 * 
	 * @author jianguo.xu
	 * @param file
	 * @param columFormatConfig
	 * @return
	 */
	public Excel parse(File file,Map<Integer, ColumnType> columFormatConfig) {
		return parse(file, columFormatConfig, false);
	}
	public Excel parse(File file,Map<Integer, ColumnType> columFormatConfig, boolean existHead) {
		try {
			InputStream ins = new FileInputStream(file);
			return parse(ins, columFormatConfig, existHead);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 解析EXCEL
	 * @author jianguo.xu
	 * @param ins
	 * @return
	 */
	public Excel parse(InputStream ins) {
		return parse(ins,null);
	}
	/**
	 * 解析EXCEL
	 * @author jianguo.xu
	 * @param ins
	 * @param columFormatConfig
	 * @return
	 */
	public Excel parse(InputStream ins,Map<Integer, ColumnType> columFormatConfig) {
		return parse(ins, columFormatConfig, false);
	}
	
	/**
	 * 解析EXCEL
	 * @author jianguo.xu
	 * @param ins
	 * @param columFormatConfig
	 * @param existHead
	 * @return
	 */
	public Excel parse(InputStream ins,
			Map<Integer, ColumnType> columFormatConfig, boolean existHead) {
		Sheet sheet = loadSheet(ins);
		return parse(sheet, columFormatConfig, existHead);
	}
	

	private Excel parse(Sheet sheet, Map<Integer, ColumnType> columFormatConfig, boolean existHead) {
		assertExcelPass(sheet);
		
		Excel excel = new Excel();
		List<List<Serializable>> excelDatas = new ArrayList<List<Serializable>>();
		
		int rowIndex = 0;
		if (existHead) {
			Cell[] cells = sheet.getRow(rowIndex);
			parseHead(excel, cells);
			rowIndex++;
		}
		
		for (; rowIndex < sheet.getRows(); rowIndex++) {
			Cell[] cells = sheet.getRow(rowIndex);
			if(isNullRow(sheet.getColumns(), cells)) 
				continue;
			List<Serializable> rowList = new ArrayList<Serializable>();
			for (Cell cell : cells) {
				rowList.add(getValue(cell, columFormatConfig));
			}
			excelDatas.add(rowList);
		}
		excel.setBody(excelDatas);
		return excel;
	}
	/**
	 * 判断是否是空行
	 * @author jianguo.xu
	 */
	private  boolean isNullRow(int columnCount,Cell[] cells) {
		for (Cell cell : cells) {
			if(!StringUtils.isNullOrEmpty(cell.getContents())) return false;
		}
		return true;
	}
	
	private void assertExcelPass(Sheet sheet) {
		if (sheet.getColumns() > excelConfig.getMaxColumnCount()
				|| sheet.getRows() > excelConfig.getMaxRowCount()) {
			throw new RuntimeException("Excel max colum is :"
					+ excelConfig.getMaxColumnCount() + "  max row is "
					+ excelConfig.getMaxRowCount());
		}
	}

	private void parseHead(Excel excel,Cell[] cells) {
		List<String> header = new ArrayList<String>();
		for(Cell cell : cells) {
			header.add(cell.getContents());
		}
		excel.setHeader(header);
		 
	}

	private Sheet loadSheet(InputStream ins) {
		Workbook work = null;
		try {
			WorkbookSettings settings = new WorkbookSettings();
			work = Workbook.getWorkbook(ins, settings);
		} catch (BiffException e) {
			throw new RuntimeException("load excel error.", e);
		} catch (IOException e) {
			throw new RuntimeException("load excel error.", e);
		}
		finally {
			if(ins!=null) {
				try {
					ins.close();
				} catch (IOException e) {
					throw new RuntimeException("close excel error.", e);
				}
			}
		}
		Sheet[] sheets = work.getSheets();
		if (sheets == null || sheets.length <= 0) {
			throw new RuntimeException("excel sheet not exist");
		}
		return sheets[0];
	}

	public Serializable getValue(Cell cell,
			Map<Integer, ColumnType> colTypeConfig)  {
		int colIndex = cell.getColumn();
		String value = cell.getContents();
		
		ColumnType columnType = colTypeConfig ==null?ColumnType.STRING:colTypeConfig.get(colIndex);
		columnType = columnType==null?ColumnType.STRING:columnType;
		try {
			if (columnType == ColumnType.STRING) {
				return value;
			}
			if (columnType == ColumnType.INT) {
				  return Integer.valueOf(value).intValue();
			}
			if (columnType == ColumnType.INT_WRAPER) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return Integer.valueOf(value);
			}
			if (columnType == ColumnType.LONG) {
				return Long.valueOf(value).longValue();
			}
			if (columnType == ColumnType.LONG_WRAPER) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return Long.valueOf(value);
			}
			if (columnType == ColumnType.BOOLEAN) {
				if(value == null) return false;
				return (value.equalsIgnoreCase("true") || value.equals("1") || value.equals("是") || value.equalsIgnoreCase("yes"))? 
						Boolean.TRUE:Boolean.FALSE;				
			}
			if (columnType == ColumnType.BOOLEAN_WRAPER) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return (value.equalsIgnoreCase("true") || value.equals("1") || value.equals("是") || value.equalsIgnoreCase("yes"))? 
						Boolean.TRUE:Boolean.FALSE;
			}
			if (columnType == ColumnType.DOUBLE) {
				return Double.valueOf(value).doubleValue();
			}
			if (columnType == ColumnType.DOUBLE_WRAPER) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return Double.valueOf(value);
			}
			if (columnType == ColumnType.BIG_DECIMAL) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return new BigDecimal(value);
			}
			if (columnType == ColumnType.DATE) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return DateUtils.parserShortDate(value, this.excelConfig.dateFormat);
			}
			if (columnType == ColumnType.DATE_TIME) {
				if(StringUtils.isNullOrEmpty(value))return null;
				return DateUtils.parserDate(value, this.excelConfig.dateTimeFormat);
			}
			return value;
		} catch (Exception e) {
			String errorMsg = "第:" + (cell.getRow() + 1)
					+ " 行 ，第" + (cell.getColumn() + 1) + "列数据格式不正确,必须为 "
					+ columnType.getType();
			throw new RuntimeException(errorMsg,e);
		}
	}
	
}
