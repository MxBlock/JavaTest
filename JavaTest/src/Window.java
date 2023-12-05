import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	Window() {
		
		this.setTitle("Fat Square");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new Pane());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
