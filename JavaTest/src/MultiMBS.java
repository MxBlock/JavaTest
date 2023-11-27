import java.awt.Color;
import java.util.Random;

public class MultiMBS extends Thread {
	double RE_START;
	double RE_END;
	double IM_START;
	double IM_END;
	double RE_RANGE;
	double IM_RANGE;
	int MAX_ITERATION;
	double ZOOM_FACTOR;

	int pixelsLength = Main.SCREEN_WIDTH * Main.SCREEN_HEIGHT;
	Color[] pixels = new Color[pixelsLength];
	int[] mousePosPixel = { 0, 0 };
	int PROCESS_COUNT = 2;

	public MultiMBS(double RE_START, double RE_END, double IM_START, double IM_END, int MAX_ITERATION,
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
	
	public void initMultiMBS() {
		
	}

	public void computeMBS(int process) {
		double c_re = 0;
		double c_im = 0;
		double z_re = 0;
		double z_im = 0;
		double z_abs = 0;
		int[] col = { 0, 0, 0 };
		double original_z_re = 0;
		int index = 0;
		
		int range_x = Main.SCREEN_HEIGHT; // PROCESS_COUNT
		int min_x = range_x * process;
		int max_x = range_x * (process + 1);

		for (int x = 0; x < Main.SCREEN_WIDTH; x++) {
			for (int y = min_x; y < max_x; y++) {
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
						double normalizedN = (double) (n) / ((double) MAX_ITERATION);
						// Mappe den normalisierten Wert auf den RGB-Bereich [0, 255]
						// Stelle sicher,dass die RGB-Werte im gültigen Bereich liegen
						col[0] = (int) (normalizedN * 255);
						col[1] = n > 255 ? (int) (normalizedN * 255) : 0;
						col[2] = n > 510 ? (int) (normalizedN * 255) : 0;
						/*
						 * // Full Color if (n <= 255) { col[0] = n; } else { col[0] = 255; if (n <=
						 * 510) {. col[1] = 255; } else { col[1] = 255; col[2] = 255; } }
						 */
						// col[0] = 255 - (n * 255 / MAX_ITERATION);
						break;
					}
					// z = z*z + c;
					// Save the original values for the next iteration
					original_z_re = z_re;
					z_re = ((z_re * z_re) - (z_im * z_im)) + c_re;
					z_im = (2 * original_z_re * z_im) + c_im;
				}
				// Calculate the index in the 1D array
				index = (int)(x * Main.SCREEN_HEIGHT + y);
				// Update the shared pixel values
				pixels[index] = new Color(col[0], col[1], col[2]);
			}
		}
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

	public void fillRandom() {
		Random rnd = new Random();
		int[] col = new int[3];

		for (int x = 0; x < pixelsLength; x++) {
			pixels[x] = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		}
	}
}
