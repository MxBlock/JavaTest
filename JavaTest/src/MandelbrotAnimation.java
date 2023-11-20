package Stuff;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotAnimation extends JFrame {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final int MAX_ITERATIONS = 1000;

	private double minRe = -2.0;
	private double maxRe = 2.0;
	private double minIm = -2.0;
	private double maxIm = 2.0;

	public MandelbrotAnimation() {
		super("Mandelbrot Zoom Animation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);

		MandelbrotPanel mandelbrotPanel = new MandelbrotPanel();
		add(mandelbrotPanel);

		Timer timer = new Timer(50, e -> {
			updateZoom();
			mandelbrotPanel.repaint();
		});
		timer.start();
	}

	private void updateZoom() {
		// Hier kannst du die Zoom-Parameter anpassen
		double zoomFactor = 0.9;
		minRe *= zoomFactor;
		maxRe *= zoomFactor;
		minIm *= zoomFactor;
		maxIm *= zoomFactor;
	}

	private class MandelbrotPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

			for (int x = 0; x < WIDTH; x++) {
				for (int y = 0; y < HEIGHT; y++) {
					double cRe = map(x, 0, WIDTH, minRe, maxRe);
					double cIm = map(y, 0, HEIGHT, minIm, maxIm);

					double xRe = cRe;
					double xIm = cIm;

					int iteration = 0;

					while (iteration < MAX_ITERATIONS && xRe * xRe + xIm * xIm < 4.0) {
						double xReNew = xRe * xRe - xIm * xIm + cRe;
						double xImNew = 2 * xRe * xIm + cIm;
						xRe = xReNew;
						xIm = xImNew;
						iteration++;
					}

					int color = (iteration == MAX_ITERATIONS) ? 0 : (iteration % 255);
					Color col = new Color(color, color, color);
					image.setRGB(x, y, col.getRGB());
				}
			}

			g.drawImage(image, 0, 0, this);
		}
	}

	private double map(double value, double start1, double stop1, double start2, double stop2) {
		return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MandelbrotAnimation().setVisible(true));
	}
}