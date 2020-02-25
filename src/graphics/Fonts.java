package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {

	public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("exshadow.ttf");
	public InputStream stream1 = ClassLoader.getSystemClassLoader().getResourceAsStream("ex.ttf");
	public InputStream stream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("ex.ttf");
	public InputStream stream3 = ClassLoader.getSystemClassLoader().getResourceAsStream("sweetCalibri.ttf");
	public InputStream stream4 = ClassLoader.getSystemClassLoader().getResourceAsStream("arrow.ttf");
	public InputStream stream5 = ClassLoader.getSystemClassLoader().getResourceAsStream("ex.ttf");
	
	public InputStream stream6 = ClassLoader.getSystemClassLoader().getResourceAsStream("SemiBold.ttf");

	public static Font font1;
	public static Font font2;
	public static Font font3;
	public static Font font3L;
	public static Font font4;
	public static Font font4L;
	public static Font arrowFont;

	public Fonts() {
		try {
			font1 = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(18f).deriveFont(Font.BOLD);
			font2 = Font.createFont(Font.TRUETYPE_FONT, stream1).deriveFont(18f).deriveFont(Font.BOLD);
			font3 = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(22f).deriveFont(Font.BOLD);
			font3L = Font.createFont(Font.TRUETYPE_FONT, stream5).deriveFont(44f).deriveFont(Font.BOLD);
			font4 = Font.createFont(Font.TRUETYPE_FONT, stream3).deriveFont(14f).deriveFont(Font.BOLD);
			font4L = Font.createFont(Font.TRUETYPE_FONT, stream6).deriveFont(14f).deriveFont(Font.ITALIC);
			arrowFont = Font.createFont(Font.TRUETYPE_FONT, stream4).deriveFont(24f).deriveFont(Font.BOLD);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
