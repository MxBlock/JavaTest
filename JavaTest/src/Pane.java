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
	private Random random;

	final int SCREEN_WIDTH = 600;
	final int SCREEN_HEIGHT = 400;
	int MAX_ITERATION = 255;
	float RE_START = -2.00f;
	float RE_END = 0.47f;
	float IM_START = -1.12f;
	float IM_END = 1.12f;
	float RE_RANGE = RE_END - RE_START;
	float IM_RANGE = IM_END - IM_START;
	Color[][] pixels = new Color[SCREEN_WIDTH][SCREEN_HEIGHT];
	
	int[] mousePosPixel = { 0, 0 };
	float ZOOM_FACTOR = 0.5f;
	int MAX_ITER = 100;
	

	Pane() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setLayout(null);
		random = new Random();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		MBS.computeNewRange(RE_START, RE_END, IM_START, IM_END, RE_RANGE, IM_RANGE, SCREEN_WIDTH, SCREEN_HEIGHT, ZOOM_FACTOR, mousePosPixel);
		MBS.computeMBS(RE_START, IM_START, RE_RANGE, IM_RANGE, SCREEN_WIDTH, SCREEN_HEIGHT, MAX_ITER, pixels);
		for (int x = 0; x < SCREEN_WIDTH; x++) {
			for (int y = 0; y < SCREEN_HEIGHT; y++) {
				g2D.setColor(pixels[x][y]);
				g2D.drawLine(x, y, x, y);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Automatisch generierter Methodenstub
		
	}
}
