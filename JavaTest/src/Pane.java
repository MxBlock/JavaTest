import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Pane extends JPanel implements MouseListener {
	MBS m = new MBS(-2, 1, -1, 1, 765, 0.5);
	//MultiThreadMBS m = new MultiThreadMBS(-2, 1, -1, 1, 765, 0.5,3);
	// private paintTest m = new paintTest();
	JLabel label;
	double[] ZoomPos = { -1.03, -0.25 };

	Pane() {
		label = new JLabel(m.RE_RANGE + "  |  " + m.IM_RANGE);
		label.setForeground(Color.WHITE);
		// label.setLocation(Main.SCREEN_WIDTH/2, Main.SCREEN_HEIGHT/2);
		this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		// this.setLayout(null);
		this.addMouseListener(this);
		this.add(label);
		this.setVisible(true);
		m.computeMBS();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		Point p = getMousePosition();
		if (p != null) {
			m.mousePosPixel[0] = p.x;
			m.mousePosPixel[1] = p.y;
			m.mousePosGraph = m.PixelToGraph(m.mousePosPixel[0], m.mousePosPixel[1]);
			label.setText(m.mousePosPixel[0] + "  |  " + (m.mousePosGraph[0]) + " -:- " + m.mousePosGraph[1] + "  |  " + m.mousePosPixel[1]);
		}
		paintCanvas(g2D, m.pixels);
		//paintGraph(g2D);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.isShiftDown()) {
			System.exit(0);
		}
		System.out.println(
				m.RE_RANGE + "  |  " + (m.mousePosGraph[0]) + " -:- " + m.mousePosGraph[1] + "  |  " + m.IM_RANGE);
		m.computeNewRange(m.mousePosGraph);
		// m.computeNewRange(ZoomPos);
		m.computeMBS();
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

	public void paintGraph(Graphics2D g2D) {
		g2D.setColor(Color.WHITE);
		g2D.drawLine(Main.SCREEN_WIDTH / 2, 0, Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		g2D.drawLine(0, Main.SCREEN_HEIGHT / 2, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT / 2);
	}
}
