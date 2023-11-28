import java.awt.Color;
import java.util.Random;

public class MBS {
	double RE_START;
	double RE_END;
	double IM_START;
	double IM_END;
	double RE_RANGE;
	double IM_RANGE;
	int MAX_ITERATION;
	double ZOOM_FACTOR;

	Color[] pixels = new Color[Main.SCREEN_LENGTH];
	int[] mousePosPixel = { 0, 0 };

	public MBS(double RE_START, double RE_END, double IM_START, double IM_END, int MAX_ITERATION,
			double ZOOM_FACTOR) {
		this.RE_START = RE_START;
		this.RE_END = RE_END;
		this.IM_START = IM_START;
		this.IM_END = IM_END;
		this.MAX_ITERATION = MAX_ITERATION;
		this.ZOOM_FACTOR = ZOOM_FACTOR;
		this.RE_RANGE = RE_END - RE_START;
		this.IM_RANGE = IM_END - IM_START;
	}

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
		double c_re = 0;
		double c_im = 0;
		double z_re = 0;
		double z_im = 0;
		double z_abs = 0;
		int[] col = { 0, 0, 0 };
		double original_z_re = 0;
		int x = 0;
		int y = 0;

		for (int i = 0; i < (Main.SCREEN_LENGTH); i++) {
			x = i % Main.SCREEN_WIDTH;
			if(x == 0) {
				y++;
			}
			// Convert pixel coordinate to graph coordinate (x=re / y=im)
			c_re = (RE_START + (((double) x / (double) Main.SCREEN_WIDTH) * RE_RANGE));
			c_im = (IM_START + (((double) y / (double) Main.SCREEN_HEIGHT) * IM_RANGE));
			z_re = 0;
			z_im = 0;
			col[0] = 0;
			col[1] = 0;
			col[2] = 0;

			// loop through z and look if it diverges or converges
			for (int n = 0; n < MAX_ITERATION; n++) {
				z_abs = Math.sqrt(z_re * z_re + z_im * z_im);
				if (z_abs > 2) {
					
					// Full Color
					if (n <= 255) {
						col[0] = n;
					} else {
						col[0] = 255;
						if (n <= 510) {
							col[1] = 255;
						} else {
							col[1] = 255;
							col[2] = 255;
						}
					}
					//col[0] = 255 - (n * 255 / MAX_ITERATION);
					break;
				}
				// z = z*z + c;
				// Save the original values for the next iteration
				original_z_re = z_re;
				z_re = ((z_re * z_re) - (z_im * z_im)) + c_re;
				z_im = (2 * original_z_re * z_im) + c_im;
			}
			// Update the pixel values
			pixels[i] = new Color(col[0], col[1], col[2]);
		}

	}

	public void fillRandom() {
		Random rnd = new Random();
		for (int i = 0; i < Main.SCREEN_LENGTH; i++) {
			pixels[i] = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		}
	}
}
