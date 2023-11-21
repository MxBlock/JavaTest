import java.awt.Color;
import java.util.Random;



public class MBS {

	double RE_START = -2;
	double RE_END = 1;
	double IM_START = -1;
	double IM_END = 1;
	double RE_RANGE = RE_END - RE_START;
	double IM_RANGE = IM_END - IM_START;

	Color[][] pixels = new Color[Main.SCREEN_WIDTH][Main.SCREEN_HEIGHT];
	int MAX_ITERATION = 100;

	int[] mousePosPixel = { 0, 0 };
	double ZOOM_FACTOR = 0.5f;

	public void computeNewRange() {
		// New middle of screen is mousePosScreen | convertP2G(mousePosition +
		// HalfNewScreen)
		RE_END = RE_START + ((mousePosPixel[0] + (Main.SCREEN_WIDTH * ZOOM_FACTOR) / 2) / Main.SCREEN_WIDTH) * RE_RANGE;
		RE_START = RE_START
				+ ((mousePosPixel[0] - (Main.SCREEN_WIDTH * ZOOM_FACTOR) / 2) / Main.SCREEN_WIDTH) * RE_RANGE;
		IM_END = IM_START
				+ ((mousePosPixel[1] + (Main.SCREEN_HEIGHT * ZOOM_FACTOR) / 2) / Main.SCREEN_HEIGHT) * IM_RANGE;
		IM_START = IM_START
				+ ((mousePosPixel[1] - (Main.SCREEN_HEIGHT * ZOOM_FACTOR) / 2) / Main.SCREEN_HEIGHT) * IM_RANGE;
		// Compute new ranges
		RE_RANGE = RE_END - RE_START;
		IM_RANGE = IM_END - IM_START;
	}
	
	public void computeMBS() {
		Complex c = new Complex(0, 0);
		Complex z = new Complex(0, 0);
		double z0 = 0;
		int[] col = new int[3];
		
		for (int x = 0; x < Main.SCREEN_WIDTH; x++) {
			for (int y = 0; y < Main.SCREEN_HEIGHT; y++) {
				// Convert pixel coordinate to complex number
				c.re = (RE_START + (x / Main.SCREEN_WIDTH) * RE_RANGE);
				c.im = (IM_START + (y / Main.SCREEN_HEIGHT) * IM_RANGE);
				z.re = 0;
				z.im = 0;
				
				for (int n = 0; n < MAX_ITERATION; n++) {

					z0 = Complex.ComplexAbs(z);
					//System.out.println(z0);
					if (z0 > 2) { // Full Color if (n <= 255) { col[0] = n; } else { col[0] = 255; if (n <=510) { col[1] = 255; } else { col[1] = 255; col[2] = 255; } }
						col[0] = 255 - (n * 255 / MAX_ITERATION);
						break;
					}

					// z = z*z + c;
					z = Complex.ComplexAdd(Complex.ComplexMul(z, z), c);
				}
				pixels[x][y] = new Color(col[0], col[1], col[2]);
			}
			System.out.println(x + "\t" +c.re + "\t" +c.im);
		}
	}
	
	public void fillRandom() {
		Random rnd = new Random();
		int[] col = new int[3];
		
		for (int x = 0; x < Main.SCREEN_WIDTH; x++) {
			for (int y = 0; y < Main.SCREEN_HEIGHT; y++) {
				pixels[x][y] = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
			}
		}
	}
}
