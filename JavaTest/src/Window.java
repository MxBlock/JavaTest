import javax.swing.JFrame;

public class Window extends JFrame {
	Window() {
		this.setTitle("Fat Square");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	this.add(new Pane());
    	this.pack();
	}
}
