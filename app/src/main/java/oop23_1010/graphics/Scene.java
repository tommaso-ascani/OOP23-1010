package oop23_1010.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

public class Scene {

	private JFrame frame;
	private ScenePanel panel;

	public Scene(int w, int h) {
		frame = new JFrame("Roll A Ball");
		frame.setSize(w, h);
		frame.setMinimumSize(new Dimension(w, h));
		frame.setResizable(false);
		// frame.setUndecorated(true); // Remove title bar
		panel = new ScenePanel(w, h);
		frame.getContentPane().add(panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(-1);
			}

			public void windowClosed(WindowEvent ev) {
				System.exit(-1);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	public void render() {
		try {
			frame.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public class ScenePanel extends JPanel {
		private int centerX;
		private int centerY;

		public ScenePanel(int w, int h) {
			setSize(w, h);
			centerX = w / 2;
			centerY = h / 2;
		}

		public void paint(Graphics g) {
			// Graphics2D g2 = (Graphics2D) g;

			// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			// RenderingHints.VALUE_ANTIALIAS_ON);
			// g2.setRenderingHint(RenderingHints.KEY_RENDERING,
			// RenderingHints.VALUE_RENDER_QUALITY);
			// g2.clearRect(0,0,this.getWidth(),this.getHeight());

			// Stroke strokeBall = new BasicStroke(4f);
			// Stroke strokePick = new BasicStroke(8f);

			// entities.stream().forEach( e -> {
			// P2d pos = e.getCurrentPos();
			// int x = (int) Math.round(centerX + pos.x * 100);
			// int y = (int) Math.round(centerY - pos.y * 100);
			// if (e instanceof Ball){
			// g2.setColor(Color.BLUE);
			// g2.setStroke(strokeBall);
			// g2.drawOval(x-20, y-20, 40, 40);
			// } else if (e instanceof PickUpObj){
			// g2.setColor(Color.RED);
			// g2.setStroke(strokePick);
			// g2.drawRect(x-20, y-20, 40, 40);
			// }
			// });

		}
	}
}
