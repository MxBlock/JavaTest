import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Pane extends JPanel implements ActionListener, MouseListener {
	private MBS m;
	
	
	Pane() {
		this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setLayout(null);
		this.addMouseListener(this);
		m = new MBS(-2, 1, -1, 1, 1500, 0.5);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		// MBS.computeNewRange(RE_START, RE_END, IM_START, IM_END, RE_RANGE, IM_RANGE,
		// SCREEN_WIDTH, SCREEN_HEIGHT, ZOOM_FACTOR, mousePosPixel);
		// MBS.computeMBS(RE_START, IM_START, RE_RANGE, IM_RANGE, SCREEN_WIDTH,
		// SCREEN_HEIGHT, MAX_ITERATION, pixels);
		
		m.computeMBS();
		// paint pixels
		for (int x = 0; x < Main.SCREEN_WIDTH; x++) {
			for (int y = 0; y < Main.SCREEN_HEIGHT; y++) {
				g2D.setColor(m.pixels[x][y]);
				g2D.drawLine(x, y, x, y);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Automatisch generierter Methodenstub

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		m.mousePosPixel[0] = e.getX();
		m.mousePosPixel[1] = e.getY();
		System.out.println(m.mousePosPixel[0] + "\t" +  m.mousePosPixel[1]);
		m.computeNewRange();
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
