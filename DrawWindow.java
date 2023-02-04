package windowStyler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class DrawWindow extends JPanel {
//	Color BrushColor = new Color.BLACK();
	private ArrayList<LineObject> lo = new ArrayList<LineObject>();
	private Map<String, Color> ColorMap = new HashMap<String, Color>();

	private Color brushColor = Color.red;

	// NOT IMPLEMETED
	public void ChangeColorRGB(int r, int g, int b) {
		this.brushColor = new Color(r, g, b);
	}

	public void ChangeColor(String str_color) {
		this.brushColor = ColorMap.get(str_color);
	}

	public void reset() {
		lo = new ArrayList<LineObject>();
		repaint();

	}

	public DrawWindow() {

		// initalize brush colors
		ColorMap.put("black", Color.black);
		ColorMap.put("blue", Color.blue);
		ColorMap.put("red", Color.red);
		ColorMap.put("green", Color.green);
		ColorMap.put("yellow", Color.yellow);
		ColorMap.put("orange", Color.orange);

		setOpaque(false);
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released");
				repaint();

			}

		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				LineObject lineobj = new LineObject();
				lineobj.addlo(e.getPoint());
				lo.add(lineobj);

				repaint();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				// add point to current drawing line object
				lo.get(lo.size() - 1).addlo(e.getPoint());

				repaint();
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(5));
		g2.setColor(brushColor);

		for (LineObject lineObj : lo) {
			int i = -1;
			ArrayList<Point> points = lineObj.getPoints();
			for (Point p : points) {
				if (i > 0) {
					g2.drawLine(p.x, p.y, points.get(i).x, points.get(i).y);
				} else {
					g2.drawLine(p.x, p.y, p.x, p.y);
				}

				i++;

			}

		}

	}

}