package com.nic.deputation.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

public class GenerateCaptchaImpl implements GenerateCaptcha {
	private String rndcode = "";
	private int rndNumber;
	public static final String CAPTCHA_KEY = "";

	private String charPool[] = { "A", "B", "C", "D", "E", "F", "0", "1", "2", "3", "a", "b", "c", "d", "e", "f", "G",
			"H", "I", "J", "K", "L", "M", "N", "g", "h", "i", "j", "k", "l", "m", "n", "O", "P", "Q", "R", "S", "T",
			"U", "V", "4", "5", "6", "7", "8", "9", "w", "x", "y", "z", "W", "X", "Y", "Z", "o", "p", "q", "r", "s",
			"t", "u", "v",

	};

	private String getrndcode() {
		rndcode = "";
		for (int i = 0; i < 6; i++) {
			rndNumber = (int) (Math.random() * 46);
			rndcode = rndcode.concat(charPool[rndNumber]);
		}
		return rndcode;
	}

	@Override
	public OutputStream getCaptcha() {
		int width = 155;
		int height = 60;
		int fontSize = 22;
		int xGap = 12;
		int yGap = 22;
		String fontName = "Arial";
		Color gradiantStartColor = new Color(128, 3, 3);
		Color gradiantEndColor = new Color(106, 2, 2);
		Color textColor = new Color(255, 255, 255);
		String[] newData = { getrndcode() };
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		GradientPaint gp = new GradientPaint(0, 0, gradiantStartColor, 0, height / 2, gradiantEndColor, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
		Random r = new Random();
		for (int i = 0; i < width - 10; i = i + 25) {
			int q = Math.abs(r.nextInt()) % width;
			int colorIndex = Math.abs(r.nextInt()) % 200;
			g2d.setColor(new Color(colorIndex, colorIndex, colorIndex));
			g2d.drawLine(i, q, width, height);
			g2d.drawLine(q, i, i, height);
		}
		g2d.setColor(textColor);
		int index = Math.abs(r.nextInt()) % newData.length;
		int x = 0;
		int y = 0;
		for (int i = 0; i < newData[index].length(); i++) {
			Font font = new Font(fontName, Font.BOLD, fontSize);
			g2d.setFont(font);
			x += xGap + (Math.abs(r.nextInt()) % 15);
			y = yGap + Math.abs(r.nextInt()) % 20;

			g2d.drawChars(newData[index].toCharArray(), i, 1, x, y);
		}
		g2d.dispose();
		return null;
	}

}
