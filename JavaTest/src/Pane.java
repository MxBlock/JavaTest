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
	private MultiMBS m;

	Pane() {
		this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setLayout(null);
		this.addMouseListener(this);
		m = new MultiMBS(-2, 1, -1, 1, 1500, 0.5);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		m.initMultiMBS();
		m.computeMBS(1);
		paintCanvas(g2D, m);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		m.mousePosPixel[0] = e.getX();
		m.mousePosPixel[1] = e.getY();
		System.out.println(m.mousePosPixel[0] + "\t" + m.mousePosPixel[1]);
		m.computeNewRange();
		repaint();
	}
//sdfsd
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

	public void paintCanvas(Graphics2D g2D, MBS m) {
		// paint pixels
		for (int x = 0; x < Main.SCREEN_WIDTH; x++) {
			for (int y = 0; y < Main.SCREEN_HEIGHT; y++) {
				g2D.setColor(m.pixels[x][y]);
				g2D.drawLine(x, y, x, y);
			}
		}
	}

	public void paintCanvas(Graphics2D g2D, MultiMBS m) {
		// paint pixels
		int x = 0;
		int y = 0;
		for (int i = 0; i < (Main.SCREEN_WIDTH * Main.SCREEN_HEIGHT); i++) {
			x = i % Main.SCREEN_WIDTH;
			if (x == 0) {
				y++;
			}
			g2D.setColor(m.pixels[x]);
			g2D.drawLine(x, y, x, y);
		}
	}
}
