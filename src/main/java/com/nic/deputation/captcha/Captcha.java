package com.nic.deputation.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CaptchaServlet", description = "Generate new captcha", urlPatterns = { "/Captcha.jpg" })
public class Captcha extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rndcode = "";
	private int rndNumber;
	public static final String CAPTCHA_KEY = "captchaRam";

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
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);

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

		String captcha = newData[index];
		req.getSession().setAttribute(CAPTCHA_KEY, captcha);
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
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(bufferedImage, "jpeg", outputStream);
		outputStream.close();
	}
}
