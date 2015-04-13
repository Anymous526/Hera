/*
 * @(#)ExcelUtils.java
 *
 * Copyright 2011 Just In Mobile, Inc. All rights reserved.
 */

package com.vlives.boss.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.vlives.boss.member.domain.Level;
import com.vlives.boss.user.domain.CardType;
import com.vlives.boss.user.domain.TempUser;
import com.vlives.core.exception.BusinessException;
import com.vlives.util.DateUtils;
import com.vlives.util.StringUtils;

/**
 * description
 * 
 * @author fyuan
 * @version 1.0,2011-5-26
 */
public class ExcelUtils {
	private static String TITLE[] = { "手机号码", "名称", "昵称", "性别", "地区", "出生年月",
			"邮政编码", "电子邮件", "MSN", "QQ", "证件类型", "证件号码", "会员等级", "会员卡号","会员积分" };

	public static List<TempUser> readExcel(InputStream in)
			throws BusinessException {
		Workbook wb = null;
		Sheet sheet = null;
		List<TempUser> list = new ArrayList<TempUser>();
		try {
			wb = Workbook.getWorkbook(in);
			sheet = wb.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				if (i == 0) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						String content = sheet.getCell(j, i).getContents();
						if (content != null && !content.trim().equals(TITLE[j])) {
							throw new BusinessException("excel文件格式不正确,列"
									+ TITLE[j] + " 顺序不正确");
						}
					}
				} else {
					TempUser user = new TempUser();
					for (int j = 0; j < sheet.getColumns(); j++) {
						String content = sheet.getCell(j, i).getContents();
						if (StringUtils.isNullOrEmpty(content))
							continue;
						else
							content = content.trim();
						switch (j) {
						case 0:
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher matcher = pattern.matcher(content);
							if(!matcher.matches() || content.length() != 11)
								throw new BusinessException("第"+(i+1)+"行,手机号码,不正确");
							user.setMobile(content);
							break;
						case 1:
							user.setName(content);
							break;
						case 2:
							user.setPetName(content);
							break;
						case 3:
							if (content.equals("男"))
								user.setGender(1);
							else if (content.equals("女"))
								user.setGender(2);
							else
								user.setGender(0);
							break;
						case 4:
							user.setAreaStr(content);
							break;
						case 5:
							try {
								Date birthday = DateUtils.parserDate(content,
										DateUtils.SHORT_DATE_FORMATE_TWO);
								user.setBirthday(birthday);
							} catch (Exception e) {
								try{
									Date birthday = DateUtils.parserDate(content,
											DateUtils.SHORT_DATE_FORMATE_ONE);
									user.setBirthday(birthday);
								}catch(Exception ee){
									throw new BusinessException("第"+(i+1)+"行,出生日期,数据格式不正确");
								}
							}

							break;
						case 6:
							user.setPost(content);
							break;
						case 7:
							user.setEmail(content);
							break;
						case 8:
							user.setMsn(content);
							break;
						case 9:
							user.setQq(content);
							break;
						case 10:
							if (content.equals("身份证"))
								user.setCardType(CardType.CARDTYPE_IDENTITY);
							else if (content.endsWith("驾照"))
								user.setCardType(CardType.CARDTYPE_DRIVER);
							break;
						case 11:
							user.setCardNumber(content);
							break;
						case 12:
							try {
								user.setLevel(Level.get(content));
							} catch (Exception e) {
								throw new BusinessException("第"+(i+1)+"行,"+content + "等级不存在");
							}
							break;
						case 13:
							user.setCardNo(content);
							break;
						case 14:
							int point = 0;
							try {
								point = Integer.parseInt(content);
							}
							catch (Exception e) {
								throw new BusinessException("第"+(i+1)+"行,"+content + "积分非数字");
							}
							if(point<0) {
								throw new BusinessException("第"+(i+1)+"行,"+content + "积分不能小于0");
							}
							user.setPoint(point);
							break;	
						}
						
					}
					if (StringUtils.isNullOrEmpty(user.getMobile())
							&& StringUtils.isNullOrEmpty(user.getCardNo())) {
						throw new BusinessException("第"+(i+1)+"行,手机号码和卡号不能同时为空");
					}else{
						list.add(user);
					}

				}
			}
		} catch (BiffException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (wb != null)
				wb.close();
		}
		checkMobileAndCard(list);
		return list;
	}

	private static void checkMobileAndCard(List<TempUser> tempUsers) throws BusinessException{
		//TODO 验证手机号是否重复，验证卡号是否重复
		for(TempUser user : tempUsers){
			String cardNo = user.getCardNo();
			if(cardNo == null) {
				continue;
			}
			int count = 0;				//记录相同字段在EXCEL中出现次数
			for(TempUser tempUser : tempUsers) {
				String tempCardNo = tempUser.getCardNo();
				if(tempCardNo == null) {
					continue;
				}
				if(cardNo.equals(tempCardNo)) {
					count++;
				}
				if(count == 2) {		//第二次出现在excel中
					throw new BusinessException("会员卡号"+cardNo+"在excel中重复");
				}
			}
		}
		
		for(TempUser user : tempUsers){
			String mobile = user.getMobile();
			if(mobile == null) {
				continue;
			}
			int count = 0;				//记录相同字段在EXCEL中出现次数
			for(TempUser tempUser : tempUsers) {
				String tempMobile = tempUser.getMobile();
				if(tempMobile == null) {
					continue;
				}
				if(mobile.equals(tempMobile)) {
					count++;
				}
				if(count == 2) {		//第二次出现在excel中
					throw new BusinessException("手机号码"+mobile+"在excel中重复");
				}
			}
		}
	}
	
}
