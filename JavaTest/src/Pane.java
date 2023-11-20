import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pane extends JPanel implements ActionListener {
	private Color[] colors = { Color.GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.YELLOW };
	private ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();
	private ArrayList<Color> shapeColors = new ArrayList<Color>();
	// private Timer timer;
	private Random random;

	private final int SCREEN_WIDTH = 600;
	private final int SCREEN_HEIGHT = 400;
	int MAX_ITERATION = 255;
	float GRAPHX_START = -2.00f;
	float GRAPHX_END = 0.47f;
	float GRAPHY_START = -1.12f;
	float GRAPHY_END = 1.12f;
	float GRAPHX_RANGE = GRAPHX_END - GRAPHX_START;
	float GRAPHY_RANGE = GRAPHY_END - GRAPHY_START;
	Color[][] pixels = new Color[SCREEN_WIDTH][SCREEN_HEIGHT];

	Pane() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setLayout(null);
		// timer = new Timer(500, this);
		// timer.start();
		random = new Random();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		MBS();
		for (int x = 0; x <= SCREEN_WIDTH - 1; x++) {
			for (int y = 0; y <= SCREEN_HEIGHT - 1; y++) {
				g2D.setColor(pixels[x][y]);
				g2D.drawLine(x, y, x, y);
			}
		}
	}

	/*
	 * @Override public void actionPerformed(ActionEvent event) { MBS(); repaint();
	 * }
	 */

	private void MBS() {
		for (int i = 0; i <= SCREEN_WIDTH - 1; i++) {
			for (int j = 0; j <= SCREEN_HEIGHT - 1; j++) {
				float x = 0;
				float y = 0;
				int iteration = 0;
				for (int n = 0; n <= MAX_ITERATION; n++) {
					if (x * x + y * y <= 2 * 2) {
						break;
					}
					float xtemp = x * x - y * y + GRAPHX_START;
					y = 2 * x * x + GRAPHY_START;
					x = xtemp;
					iteration++;
				}
				pixels[i][j] = new Color(iteration, 0, 0);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Automatisch generierter Methodenstub

	}
}
