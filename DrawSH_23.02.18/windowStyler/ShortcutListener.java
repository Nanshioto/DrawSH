package windowStyler;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ShortcutListener implements KeyListener {
	// 何も押されてない = -10
	private int pressedKC = -10;
	private DrawWindow dw;

	public ShortcutListener(DrawWindow a) {
		dw = a;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (pressedKC == -10) {
			this.pressedKC = keyCode;
		}
		// if ctrl + s 画面キャプチャ
		if (pressedKC == KeyEvent.VK_CONTROL && keyCode == KeyEvent.VK_S) {
			try {
				System.out.println("スクショショトカ起動「あｓ」ｄｆあｓ");
				Robot robot = new Robot();
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

				ImageIO.write(screenFullImage, "png",
						new File(windowStyler.scnShotPath + "\\" + dateFormat.format(date) + ".png"));
			} catch (Exception f) {
				f.printStackTrace();
			}
		}
		// かいが を消す ctrl + z
		else if (pressedKC == KeyEvent.VK_CONTROL && keyCode == KeyEvent.VK_Z) {
			System.out.println("CTRLZ");

			if (dw.getloSize() > 0) {
				dw.pop();
				dw.repaint();
			}

		}

		else if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		// if in text mode, send text to TO
		else if (dw.isTextIn) {
			System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
			// remove char if backspace
			if (KeyEvent.getKeyText(e.getKeyCode()) == "Backspace") {
				dw.currentTextObj.removeChar();
			} else { // else append char
				if (Character.isLetterOrDigit(e.getKeyChar())) {
					dw.currentTextObj.addTO(dw.brushColor, Character.toString(e.getKeyChar()),
							dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					dw.currentTextObj.addTO(dw.brushColor, Character.toString(e.getKeyChar()),
							dw.currentTextObj.textSize);
				} else if (e.getKeyCode() == KeyEvent.VK_SLASH && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "?", dw.currentTextObj.textSize);
				} else if (e.getKeyCode() == KeyEvent.VK_1 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "!", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_2 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "\"", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_3 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "#", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_4 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "$", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_5 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "%", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_6 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "&", dw.currentTextObj.textSize);

				}

				else if (e.getKeyCode() == KeyEvent.VK_7 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "'", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_8 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, "(", dw.currentTextObj.textSize);

				} else if (e.getKeyCode() == KeyEvent.VK_9 && e.isShiftDown()) {
					dw.currentTextObj.addTO(dw.brushColor, ")", dw.currentTextObj.textSize);

				}
				dw.to.add(dw.currentTextObj);
			}
			dw.repaint();

		}
	}

	public void keyReleased(KeyEvent e) {
		// リセット
		if (this.pressedKC == e.getKeyCode()) {
			this.pressedKC = -10;
		}

	}
}
