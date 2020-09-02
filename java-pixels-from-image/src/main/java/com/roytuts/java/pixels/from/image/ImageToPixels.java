package com.roytuts.java.pixels.from.image;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageToPixels {

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
		BufferedImage bufferedImage = getImage("Chrysanthemum.jpg");
		
		/**
		 * Get pixels from the above image and display in the console
		 */
		int[][] pixels = getImageToPixels(bufferedImage);
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				System.out.print(pixels[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Read image and return BufferedImage imageFullPath - full path of the image
	 * with image file name
	 */
	public static BufferedImage getImage(String imageFullPath) {
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
	 * Get pixels in two dimensional array bufferedImage - get the BufferedImage
	 * instance from the image
	 */
	public static int[][] getImageToPixels(BufferedImage bufferedImage) {
		if (bufferedImage == null) {
			throw new IllegalArgumentException();
		}
		int h = bufferedImage.getHeight();
		int w = bufferedImage.getWidth();
		int[][] pixels = new int[h][w];
		for (int i = 0; i < h; i++) {
			/**
			 * get pixels from image
			 */
			bufferedImage.getRGB(0, i, w, 1, pixels[i], 0, w);
		}
		return pixels;
	}

	/**
	 * Check a file is an image imageName - image file name with extension
	 */
	private static boolean isFileAnImage(String imageName) {
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
