package windowStyler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class getMousePoints extends JPanel {
	public void run() {

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();
			}
		});

	}
}
