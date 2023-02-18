package windowStyler;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextInputListener implements KeyListener {
	// 何も押されてない = -10
	private int pressedKC = -10;
	private DrawWindow dw;

	public TextInputListener(DrawWindow a) {
		dw = a;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		String c_str = Character.toString(c);
		
		
		dw.pop();
		dw.repaint();

	}

	public void keyReleased(KeyEvent e) {
		// リセット
		if (this.pressedKC == e.getKeyCode()) {
			this.pressedKC = -10;
		}

	}
}
