import java.awt.Color;

public class MBS {
	public static void computeNewRange(float RE_START, float RE_END, float IM_START, float IM_END, float RE_RANGE, float IM_RANGE, int SCREEN_WIDTH, int SCREEN_HEIGHT, float ZOOM_FACTOR, int[] mousePosPixel) {
		// New middle of screen is mousePosScreen | convertP2G(mousePosition +
		// HalfNewScreen)
		RE_END = RE_START + ((mousePosPixel[0] + (SCREEN_WIDTH * ZOOM_FACTOR) / 2) / SCREEN_WIDTH) * RE_RANGE;
		RE_START = RE_START + ((mousePosPixel[0] - (SCREEN_WIDTH * ZOOM_FACTOR) / 2) / SCREEN_WIDTH) * RE_RANGE;
		IM_END = IM_START + ((mousePosPixel[1] + (SCREEN_HEIGHT * ZOOM_FACTOR) / 2) / SCREEN_HEIGHT) * IM_RANGE;
		IM_START = IM_START + ((mousePosPixel[1] - (SCREEN_HEIGHT * ZOOM_FACTOR) / 2) / SCREEN_HEIGHT) * IM_RANGE;
		// Compute new ranges
		RE_RANGE = RE_END - RE_START;
		IM_RANGE = IM_END - IM_START;
	}

	public static void computeMBS(float RE_START, float IM_START, float RE_RANGE, float IM_RANGE, int SCREEN_WIDTH, int SCREEN_HEIGHT,int MAX_ITER,  Color[][] pixels) {

		for (int x = 0; x < SCREEN_WIDTH; x++) {
			for (int y = 0; y < SCREEN_HEIGHT; y++) {
				// Convert pixel coordinate to complex number
				Complex c = new Complex((RE_START + (x / SCREEN_WIDTH) * RE_RANGE), (IM_START + (y / SCREEN_HEIGHT) * IM_RANGE));
				int color = 0;
				Complex z = 0;
				int[] col = { 0, 0, 0 };
				for (int n = 0; n < MAX_ITER; n++) {
					if (Math.abs(z) > 2) {
						/*
						 * match COLOR_MODE: case 1: color = 255 if n < MAX_ITER else 0 col =
						 * [color,color,color] break case 2: color = 255 - int(n * 255 / MAX_ITER) col =
						 * [color,color,color] break case 3: if n <= 255: col[0] = n else: col[0] = 255
						 * if n <= 510: col[1] = 255 else: col[1] = 255 col[2] = 255 break
						 */
						// The color depends on the number of iterations
						// color = 255 - int(n * 255 / MAX_ITER)

						// Fast color mode - black/white
						if (n < MAX_ITER) {
							color = 255;
						} else {
							color = 0;
						}

						/*
						 * // Full Color if n <= 255: col[0] = n; else: col[0] = 255; if n <= 510:
						 * col[1] = 255; else: col[1] = 255; col[2] = 255; break;
						 */
					}
					
					//z = z*z + c;
					z = ComplexAdd(ComplexMul(z,z),c);
				}
				pixels[x][y] = new Color(color, 0, 0);
			}

			// t1 = time.time();
			// print(str(t1-t0)+ "\t""\t" + str(RE_START) + "\t" + str(RE_END) + "\t" +
			// str(RE_RANGE) + "\t\t" + str(IM_START) + "\t" + str(IM_END) + "\t" +
			// str(IM_RANGE));

		}
	}
}
