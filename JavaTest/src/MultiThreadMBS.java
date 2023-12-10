import java.awt.Color;
import java.util.Random;

public class MultiThreadMBS extends Thread{
	double RE_START;
	double RE_END;
	double IM_START;
	double IM_END;
	double RE_RANGE;
	double IM_RANGE;
	int MAX_ITERATION;
	double ZOOM_FACTOR;
	int THREAD_COUNT;

	Color[] pixels = new Color[Main.SCREEN_LENGTH];
	double[] mousePosGraph = { 0, 0 };
	int[] mousePosPixel = { 0, 0 };
	double ZoomedScreenHalfWidth = 0;
	double ZoomedScreenHalfHeight = 0;

	public MultiThreadMBS(double RE_START, double RE_END, double IM_START, double IM_END, int MAX_ITERATION, double ZOOM_FACTOR, int THREAD_COUNT) {
		this.RE_START = RE_START;
		this.RE_END = RE_END;
		this.IM_START = IM_START;
		this.IM_END = IM_END;
		this.MAX_ITERATION = MAX_ITERATION;
		this.ZOOM_FACTOR = ZOOM_FACTOR;
		this.RE_RANGE = RE_END - RE_START;
		this.IM_RANGE = IM_END - IM_START;
		this.THREAD_COUNT = THREAD_COUNT;
	}

	// Compute Zoomed in Values for Graph
	public void computeNewRange(double[] GraphZoomPos) {
		double RE_ZoomedGraphHalf = (ZOOM_FACTOR * 0.5) * RE_RANGE;
		double IM_ZoomedGraphHalf = (ZOOM_FACTOR * 0.5) * IM_RANGE;
		
		RE_END = GraphZoomPos[0] + RE_ZoomedGraphHalf;
		RE_START = GraphZoomPos[0] - RE_ZoomedGraphHalf;
		IM_END = GraphZoomPos[1] + IM_ZoomedGraphHalf;
		IM_START = GraphZoomPos[1] - IM_ZoomedGraphHalf;
		RE_RANGE = RE_END - RE_START;
		IM_RANGE = IM_END - IM_START;
	}

	public void computeMBS() {
		computeMbsPart(1);
	}
	
	public void computeMbsPart(int process) {
		double c_re = 0;
		double c_im = 0;
		double z_re = 0;
		double z_im = 0;
		double z_abs = 0;
		int[] col = { 0, 0, 0 };
		double original_z_re = 0;
		int x = 0;
		int y = 0;
		
		int range = Main.SCREEN_HEIGHT; // PROCESS_COUNT
		int min = range * process;
		int max = range * (process + 1);

		for (int i = min; i < max; i++) {
			x = i % Main.SCREEN_WIDTH;
			if (x == 0) {
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
					// col[0] = 255 - (n * 255 / MAX_ITERATION);
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

	// Fill Pixels Array with random values
	public void fillRandom() {
		Random rnd = new Random();
		for (int i = 0; i < Main.SCREEN_LENGTH; i++) {
			pixels[i] = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		}
	}

	// Convert Graph-Coordinate to Pixel-Coordinate
	public int[] GraphToPixel(double re, double im) {
		int x = (int) (Math.abs(re) / RE_RANGE * Main.SCREEN_WIDTH);
		int y = (int) (Math.abs(im) / IM_RANGE * Main.SCREEN_HEIGHT);
		int[] n = { x, y };
		return n;
	}

	// Convert Pixel-Coordinate to Graph-Coordinate
	public double[] PixelToGraph(int x, int y) {
		double re = (RE_START + (((double) x / (double) Main.SCREEN_WIDTH) * RE_RANGE));
		double im = (IM_START + (((double) y / (double) Main.SCREEN_HEIGHT) * IM_RANGE));
		double[] n = { re, im };
		return n;
	}
}
