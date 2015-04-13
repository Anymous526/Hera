/*
 * @(#)ZIPUtil.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.vlives.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * description
 * 
 * @author xujianguo
 * @version 1.0,2008-12-5
 */
public class ZIPUtils {
	/**
	 * 文件或文件夹的压缩
	 * @param 压缩文件名字
	 * @param 被压缩文件或文件夹
	 * @throws IOException
	 */
	public static void zip(String zipFileName, String destPath)
			throws IOException {
		FileOutputStream f = new FileOutputStream(zipFileName);
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(csum);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		File file = new File(destPath);
		if(!file.exists()) {
			throw new IOException("目标文件或文件夹不存在");
		}
		zip(zos, out, file,destPath);
		out.close();
	}
	
	private static void zip(ZipOutputStream zos, BufferedOutputStream out,
			File destPath,String originalPath) throws IOException {
		if (destPath.isDirectory()) {
			File[] files = destPath.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isDirectory()) {
					zip(zos, out, file.getAbsoluteFile(),originalPath);
				} else {
					zipFile(zos, out,file.getAbsoluteFile(),originalPath);
				}
			}
		}
		else {
			zipFile(zos, out,destPath,originalPath);
		}
	}
	
	private static void zipFile(ZipOutputStream zos, BufferedOutputStream out,
			File destPath,String originalPath) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(destPath));
		zos.putNextEntry(new ZipEntry(destPath.getAbsolutePath().substring(originalPath.length())));
		int c;
		while ((c = in.read()) != -1)
			out.write(c);
		in.close();
	}
	/**
	 * 解压缩
	 * @param 解压文件
	 * @param 目标路径
	 * @throws IOException
	 */
	public static void unZip(File zipFile, String extPlace,boolean reservZipFile)
			throws IOException {		
		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry entry = null;
		while ((entry = in.getNextEntry()) != null) {
			String entryName = entry.getName();
			if (entry.isDirectory()) {
				File file = new File(extPlace + entryName);
				file.mkdirs();
			} else {
				File file = new File(extPlace + entryName);
				if (!file.isFile()) {
					file = file.getAbsoluteFile();
					File parent = file.getParentFile();
					if (!parent.exists()) {
						parent.mkdirs();
					}
				}
				OutputStream os = new FileOutputStream(extPlace + entryName);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					os.write(buf, 0, len);
				}
				os.close();
				in.closeEntry();
			}
		}
		in.close();
		if(!reservZipFile) {
			zipFile.delete();
		}
	}
	/**
	 * 判断压缩文件中受否有制定文件(深度遍历)
	 * @param 压缩文件
	 * @param 指定的文件
	 * @param 是否忽略大小写
	 * @return
	 * @throws IOException
	 */
	public static boolean existFile(File zipFile, String checkFile,
			boolean ignoreCase) throws IOException {
		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry entry = null;

		while ((entry = in.getNextEntry()) != null) {
			String entryName = entry.getName();
			if (ignoreCase) {
				if (entryName.toUpperCase().indexOf(checkFile.toUpperCase()) != -1) {
					return true;
				}
			} else {
				if (entryName.indexOf(checkFile) != -1) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 判断压缩文件的根目录中是否存在指定文件
	 * @param 压缩文件
	 * @param 指定的文件
	 * @param 是否忽略大小写
	 * @return
	 * @throws IOException
	 */
	public static boolean existFileByRoot(File zipFile, String checkFile,
			boolean ignoreCase) throws IOException {
		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry entry = null;
		while ((entry = in.getNextEntry()) != null) {
			String entryName = entry.getName();
			if (ignoreCase) {
				if (entryName.toUpperCase().equals(checkFile.toUpperCase())) {
					return true;
				}
			} else {
				if (entryName.equals(checkFile)) {
					return true;
				}
			}
		}
		return false;
	}	
}