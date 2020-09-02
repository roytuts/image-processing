package com.roytuts.java.read.write.image;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageReadWrite {

	private static final String IMAGE_EXT_JPG = "jpg";
	private static final String IMAGE_EXT_JPEG = "jpeg";
	private static final String IMAGE_EXT_PNG = "png";
	private static final String IMAGE_EXT_GIF = "gif";
	/**
	 * Exception message to be thrown when allowed image types are not read
	 */
	public static final String IMAGE_ALLOW_TYPES = "Image types allowed - " + IMAGE_EXT_JPG + IMAGE_EXT_JPEG
			+ IMAGE_EXT_PNG + IMAGE_EXT_GIF;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Read the image, Desert.jpg, from Desktop/Pictures
		 */
		BufferedImage bufferedImage = readImage("Desert.jpg");
		/**
		 * Write to the image file, DesertOutput.jpg, to desktop
		 */
		boolean isSuccess = writeImage(bufferedImage, "DesertOutput.jpg", IMAGE_EXT_JPEG);
		/**
		 * Show success or failed message
		 */
		if (isSuccess) {
			System.out.println("Image successfully written.");
		} else {
			System.out.println("Image writing failed.");
		}
	}

	/**
	 * Read image and return BufferedImage imageFullPath - full path of the image
	 * with image file name
	 */
	public static BufferedImage readImage(String imageFullPath) {
		BufferedImage bufferedImage = null;
		try {
			if (imageFullPath == null) {
				throw new NullPointerException("Image full path cannot be null or empty");
			}
			/**
			 * Check if the selected file is an image
			 */
			boolean isImage = isFileAnImage(imageFullPath);

			if (!isImage) {
				throw new ImagingOpException(IMAGE_ALLOW_TYPES);
			}

			String imagePath = imageFullPath;

			/**
			 * get BufferedImage and return it
			 */
			bufferedImage = ImageIO.read(new File(imagePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	/**
	 * Write to the image file bufferedImage - image outputPath - where image has to
	 * be written fileName - output file name with extension format - output image
	 * file format
	 */
	public static boolean writeImage(BufferedImage bufferedImage, String fileName, String format) {
		boolean isSuccess = true;
		if (bufferedImage == null || format == null) {
			throw new IllegalArgumentException();
		}
		try {
			File file = new File(fileName);
			/**
			 * write BufferedImage to a file
			 */
			ImageIO.write(bufferedImage, format, file);
		} catch (Exception e) {
		}
		return isSuccess;
	}

	/**
	 * Check a file is an image imageName - image file name with extension
	 */
	public static boolean isFileAnImage(String imageName) {
		if (imageName == null) {
			throw new NullPointerException("Image full path cannot be null or empty");
		}
		File imageFile = new File(imageName);
		String ext = getFileExtension(imageFile);
		if (IMAGE_EXT_GIF.equalsIgnoreCase(ext) || IMAGE_EXT_JPEG.equalsIgnoreCase(ext)
				|| IMAGE_EXT_JPG.equalsIgnoreCase(ext) || IMAGE_EXT_PNG.equalsIgnoreCase(ext)) {
			return true;
		}
		return false;
	}

	/**
	 * Get file extension from the file
	 *
	 * @param file - file
	 */
	public static String getFileExtension(File file) {
		if (file == null) {
			throw new NullPointerException("Image file cannot be null");
		}
		String name = file.getName();
		int lastDotIndex = name.lastIndexOf(".");
		if (lastDotIndex > 0 && lastDotIndex < (name.length() - 1)) {
			return name.substring(lastDotIndex + 1).toLowerCase();
		}
		return "";
	}

}
