import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pane extends JPanel implements ActionListener {
	private MBS m;
	
	
	Pane() {
		this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setLayout(null);
		m = new MBS();
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
}
