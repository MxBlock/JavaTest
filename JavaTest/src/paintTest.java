import java.awt.Color;
import java.util.Random;

public class paintTest {

	Color[] pixels = new Color[Main.SCREEN_LENGTH];
	int[] mousePosPixel = { 0, 0 };

	public paintTest() {
	}

	public void computeNewRange() {
	}

	public void computeMBS() {
		int index = 0;
		int x = 0;
		int y = 0;

		for (int i = 0; i < Main.SCREEN_LENGTH; i++) {
			x = i % Main.SCREEN_WIDTH;
			y = i - x * Main.SCREEN_HEIGHT;
			if (x == 0) {
				y++;
			}
			// Update the shared pixel values
			pixels[i] = new Color(255, 0, 0);
		}

	}

	public void fillRandom() {
		Random rnd = new Random();
		for (int i = 0; i < Main.SCREEN_LENGTH; i++) {
			pixels[i] = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		}
	}
}