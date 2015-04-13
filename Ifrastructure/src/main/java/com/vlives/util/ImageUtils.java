/**
 * @(#)ImageUtil123.java
 *
 * Copyright 2011 JUST IN MOBILE, Inc. All rights reserved.
 */
package com.vlives.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * description
 * @author  jianguo.xu
 * @version 1.0,2011-4-14
 */
public class ImageUtils {
	private static final float MAX_SCAL = 20;
	/**
	 * 图片格式
	 * @author jianguo.xu
	 *
	 */
	public static enum FormatType{
		JPG("jpg"), JPEG("jpeg"), GIF("gif"), BMP("bmp"), PNG("png");
		private final String name;
		
		private FormatType(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
	/**
	 * 位置
	 * @author jianguo.xu
	 *
	 */
	public static enum Position {
		CENTER,LEFT,LEFT_TOP,TOP,RIGHT_TOP,RIGHT,RIGHT_BOTTOM,BOTTOM,LEFT_BOTTOM;
	}
	
	/**
	 * @see #zoom(String, String, int, int, boolean)
	 * <p>按比例缩放图片</p>
	 * <p>如果width和height的缩放比率不同则用背景色填充</p>
	 * @author jianguo.xu
	 * @param srcImageName 		原始图片文件
	 * @param descImageName 	目前图片文件(请不加加后缀名,后缀名将自动加上)
	 * @param width 			压缩的宽度
	 * @param height 			压缩的高度
	 * @param backGroundColor	填充的背景色
	 * @param imgPosition		图片相对于背景图片的位置
	 */
	public static void zoomAndFillBack(String srcImageName,String descImageName,int width,int height,Color backGroundColor,Position topImgPosition) {
		checkParam(width,height);
		BufferedImage srcImage = createBufferedImage(srcImageName);
		float scale= calScale(srcImage, width, height);
		BufferedImage outImage = scale(srcImage, scale);
		if(isNeedFillBack(srcImage,width,height)) {
			 
			outImage = fillBackGroup(outImage, width, height, backGroundColor, topImgPosition);
			
		}
		writeImage(outImage, descImageName,FormatType.JPG);
	}
	private static BufferedImage fillBackGroup(BufferedImage topImage, int backWidth,
			int backHeight, Color backGroundColor,Position topImgPosition){
		int topWidth = topImage.getWidth();
		int topHeight = topImage.getHeight();
		int backGroundWidth = getBackGroundWidth(topWidth, backWidth);
		int backGroundHeight = getBackGroundHeight(topHeight, backHeight);
		int[] topImageArray = new int[topWidth * topHeight];
		topImageArray = topImage.getRGB(0, 0, topWidth, topHeight,
				topImageArray, 0, topWidth);
		BufferedImage backgroundImage = createBackGroundImage(backWidth,
				backHeight, backGroundColor);
		int[] backGroundImageArray = new int[backGroundWidth * backGroundHeight];
		backGroundImageArray = backgroundImage.getRGB(0, 0, backGroundWidth,
				backGroundHeight, backGroundImageArray, 0, backGroundWidth);
		int topX = getXPosition(topWidth, backWidth, topImgPosition);
		int topY = getYPosition(topHeight, backHeight, topImgPosition);
		backgroundImage.setRGB(topX, topY, topWidth,
				topHeight, topImageArray, 0, topWidth);
		return backgroundImage;
	}
	
	private static int getXPosition(int topWidth,int backWidth,Position topImgPosition) {
		 
		if(topImgPosition == Position.LEFT||topImgPosition == Position.LEFT_TOP||topImgPosition == Position.LEFT_BOTTOM) {
			 return 0;
		}
		if(topImgPosition == Position.CENTER||topImgPosition == Position.TOP||topImgPosition == Position.BOTTOM) {
			 return (int) ( (float) backWidth / 2 - (float) topWidth / 2);
		}
		return backWidth - topWidth;
	}
	
	private static int getYPosition(int topHeight,int backHeight,Position topImgPosition) {
		if(topImgPosition == Position.LEFT_TOP||topImgPosition == Position.TOP||topImgPosition == Position.RIGHT_TOP) {
			 return 0;
		}
		if(topImgPosition == Position.CENTER||topImgPosition == Position.LEFT||topImgPosition == Position.RIGHT) {
			return (int) ((float)backHeight / 2 - (float)topHeight / 2);
		}
		return backHeight - topHeight;
	}

	private static int getBackGroundWidth(int topWidth,int backWidth) {
		return backWidth > topWidth ? backWidth : topWidth;
	}
	
	private static int getBackGroundHeight(int topHeight,int backHeight) {
		return backHeight > topHeight ? backHeight : topHeight;
	}
	
	/**
	 * 判断是否需要背景填充
	 * @author jianguo.xu
	 * @param srcImage
	 * @param width
	 * @param height
	 * @return
	 */
	private static boolean isNeedFillBack(BufferedImage srcImage,int width,int height) {
		int srcWidth = srcImage.getWidth();
		int srcHeight = srcImage.getHeight();
		float widthScale =  (float)width/srcWidth;
		float heightScal =  (float)height/srcHeight;
		return  widthScale == heightScal?false:true;
	}
	
	/**
	 * 创建一个指定宽度、高度、背景色的图片
	 * @author jianguo.xu
	 * @param width 
	 * @param height
	 * @param backGroundColor
	 * @return
	 */
	private static BufferedImage createBackGroundImage(int width, int height,
			Color backGroundColor) {
		BufferedImage bimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.setBackground(backGroundColor);
		g.clearRect(0, 0, width, height);
		g.dispose();
		return bimage;
	}
	
	/**
	 * <p>采用JPEG格式的图像缩放</p>
	 * <p>根据width和height计算缩放的比率,</p>
	 * <p>如果rate=true,则图像的缩放规则为：</p>
	 * <p>1、如果 图像宽的缩放比率为放大；则以放大率高的为最终比率</p>
	 * <p>2、如果图像的宽为缩小而高为放大；则以宽的缩小比率为最终比率</p>
	 * <p>3、如果图像的宽和高都为缩小；则以缩小率高的最终比率</p>
	 * @author jianguo.xu
	 * @param srcImageName 原始图片文件
	 * @param descImageName 目前图片文件(请不加加后缀名,后缀名将自动加上)
	 * @param width 压缩的宽度
	 * @param height 压缩的高度
	 * @param rate 是否是按比率缩放(true：按比率缩放，按实际的width和height来缩放)
	 */
	public static void zoom(String srcImageName,String descImageName,int width,int height,boolean rate){
		checkParam(width,height);
		BufferedImage srcImage = createBufferedImage(srcImageName);
		BufferedImage outImage = null;
		if(rate) {
			float scale= calScale(srcImage, width, height);
			outImage = scale(srcImage, scale);
		}
		else {
			outImage = scale(srcImage, width,height);
		}
		writeImage(outImage, descImageName,FormatType.JPG);
	}
	
	 
	
	private static void checkParam(int width,int height) {
		if(width>9999||width<=0) {
			throw new RuntimeException("width must between 0 and 99999");
		}
		if(height>9999||height<=0) {
			throw new RuntimeException("height must between 0 and 99999");
		}
	}
	/**
	 * 缩放规则:
	 * 1、如果 图像宽的缩放比率为放大；则以放大率高的为最终比率
	 * 2、如果图像的宽为缩小而高为放大；则以宽的缩小比率为最终比率
	 * 3、如果图像的宽和高都为缩小；则以缩小率高的最终比率
	 * 
	 * @author jianguo.xu
	 * @param srcImage
	 * @param width
	 * @param height
	 * @return
	 */
	private static float calScale(BufferedImage srcImage,int width,int height) {
		int srcWidth = srcImage.getWidth();
		int srcHeight = srcImage.getHeight();
		float widthScale = (float)width/srcWidth;
		float heightScal = (float)height/srcHeight;
		if(widthScale>=1) {
			return widthScale>=heightScal?widthScale:heightScal;
		}
		if(widthScale<1&&heightScal>=1) {
			return widthScale;
		}
		return widthScale<=heightScal?widthScale:heightScal;
	}
	
	/**
	 * 采用JPEG格式的图像缩放
	 * @author jianguo.xu
	 * @param srcImageName 原始图片文件
	 * @param descImageName 目前图片文件(请不加加后缀名,后缀名将自动加上)
	 * @param scale 压缩比例 20>sale>0
	 */
	public static void zoom(String srcImageName,String descImageName,float scale,FormatType formatType) {
		checkParam(scale);
		BufferedImage srcImage = createBufferedImage(srcImageName);
		BufferedImage outImage = scale(srcImage, scale);
		writeImage(outImage, descImageName,formatType);
	}
	
	/**
	 * 采用JPEG格式的图像缩放
	 * @author jianguo.xu
	 * @param srcImageName 原始图片文件
	 * @param descImageName 目前图片文件(请不加加后缀名,后缀名将自动加上)
	 * @param scale 压缩比例 20>sale>0
	 */
	public static void zoom(String srcImageName,String descImageName,float scale) {
		checkParam(scale);
		BufferedImage srcImage = createBufferedImage(srcImageName);
		BufferedImage outImage = scale(srcImage, scale);
		writeImage(outImage, descImageName,FormatType.JPG);
	}
	
	private static void writeImage(BufferedImage outImage,String descImageName,FormatType formatType) {
		try {
			ImageIO.write(outImage, formatType.getName(), new File(descImageName+"."+formatType.getName()));
		} catch (IOException e) {
			throw new RuntimeException("write desc image error.",e);
		}
	}
	
	
	private static BufferedImage scale(BufferedImage srcImage, float scale) {
		int width = (int) (srcImage.getWidth()*scale);
		int height = (int) (srcImage.getHeight()*scale);
		return scale(srcImage, width, height);
	}
	
	private static BufferedImage scale(BufferedImage srcImage, int width,int height) {
		Image image = srcImage.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		BufferedImage bimage = new BufferedImage(width, height, srcImage.getType());
		Graphics g = bimage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bimage;
	}
	
	private static void checkParam(float scale) {
		if(scale<=0) {
			throw new RuntimeException("scal not less than 0");
		}
		if(scale>MAX_SCAL) {
			throw new RuntimeException("scal not more than "+MAX_SCAL);
		}
	}
	
	private static BufferedImage createBufferedImage(String srcImageName) {
		try {
			return ImageIO.read(new File(srcImageName));
		} catch (IOException e) {
			throw new RuntimeException("read src image error.",e);
		}
	}
	
	/*public static void main(String[] args) throws ImageFormatException, IOException {
		zoom("d:/home/DSC_0039.JPG", "D:/home/t13.jpg", 500,300,true);
		zoomAndFillBack("d:/home/DSC_0039.JPG", "D:/home/t14.jpg", 300, 500, Color.WHITE, Position.RIGHT_BOTTOM);
		zoomAndFillBack("d:/home/DSC_0039.JPG", "D:/home/t15.jpg", 500, 300, Color.WHITE, Position.RIGHT_BOTTOM);
	}*/
}
