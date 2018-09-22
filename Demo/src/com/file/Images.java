package com.file;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.sun.image.codec.jpeg.*;

import javax.imageio.ImageIO;

public class Images {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String imagesin1 = "F:\\Image\\Image01.jpg";
		String imagesin2 = "F:\\Image\\Image02.jpg";
		String imagesout = "F:\\Image\\Image03.jpg";
		System.out.println(imagesin1);
		System.out.println(imagesin2);
		System.out.println(imagesout);
		addImages(imagesin1,imagesin2,imagesout);
	}
	
	public static void addImages (String imagesin1,String imagesin2,String imagesout){
		FileOutputStream fos = null;
		try {
			File imageFile1 = new File(imagesin1);
			File imageFile2 = new File(imagesin2);
			Image src1 = ImageIO.read(imageFile1);
			Image src2 = ImageIO.read(imageFile2);
			int width1 = src1.getWidth(null);
			int height1 = src1.getHeight(null);
			int width2 = src2.getWidth(null);
			int height2 = src2.getHeight(null);
			BufferedImage bufimg = new BufferedImage(width1,height1, BufferedImage.TYPE_INT_BGR);
			Graphics2D graphic = bufimg.createGraphics();
			graphic.drawImage(src1,0, 0,width1,height1,null);
			graphic.drawImage(src2,(width1-width2)/2, (height1-height2)/2,width2,height2,null);
			graphic.dispose();
			fos = new FileOutputStream(new File(imagesout));
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(bufimg);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
