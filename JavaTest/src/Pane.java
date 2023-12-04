import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Pane extends JPanel implements MouseListener {
	private MBS m = new MBS(-2, 1, -1, 1, 765, 0.5);
	// private Fraktall m = new Fraktall(-2, 1, -1, 1, 1000, 0.5);
	// private paintTest m = new paintTest();

	Pane() {
		this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setLayout(null);
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		m.computeMBS();
		// m.fillRandom();
		paintCanvas(g2D, m.pixels);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.isShiftDown()) {
			System.exit(0);
		}

		m.mousePosPixel[0] = e.getX();
		m.mousePosPixel[1] = e.getY();
		m.mousePosGraph[0] = (m.RE_START + (((double) m.mousePosPixel[0] / (double) Main.SCREEN_WIDTH) * m.RE_RANGE));
		m.mousePosGraph[1] = (m.IM_START + (((double) m.mousePosPixel[1] / (double) Main.SCREEN_HEIGHT) * m.IM_RANGE));
		System.out.println(m.mousePosPixel[0] + "\t" + m.mousePosPixel[1] + "\t" + m.mousePosGraph[0] + "\t" + m.mousePosGraph[1]);
		//m.computeNewRange();
		//m.computeNewRange(m.G2P(-0.75, 0.1));
		//m.computeNewRange(m.G2P(-1.0, -0.25));
		//m.computeNewRange(m.G2P(-0.7325825989246367, -0.241147130727768));
		m.computeNewRange(m.G2P(0, 0));
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void paintCanvas(Graphics2D g2D, Color[][] pixels) {
		// paint pixels
		for (int x = 0; x < Main.SCREEN_WIDTH; x++) {
			for (int y = 0; y < Main.SCREEN_HEIGHT; y++) {
				g2D.setColor(pixels[x][y]);
				g2D.drawLine(x, y, x, y);
			}
		}
	}

	public void paintCanvas(Graphics2D g2D, Color[] pixels) {
		// paint pixels
		int x = 0;
		int y = 0;
		for (int i = 0; i < (Main.SCREEN_LENGTH); i++) {

			x = i % Main.SCREEN_WIDTH;
			if (x == 0) {
				y++;
			}
			g2D.setColor(pixels[i]);
			g2D.drawLine(x, y, x, y);
		}
	}
}
