package windowStyler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawWindow extends JPanel {
	private static final long serialVersionUID = 852536344797789121L;
	private ArrayList<LineObject> lo = new ArrayList<LineObject>();
	ArrayList<TextObject> to = new ArrayList<TextObject>();
	private Map<String, Color> ColorMap = new HashMap<String, Color>();
	Color brushColor = new Color(209, 55, 57);
	int brushSize = 5;
	TextObject currentTextObj;

	public boolean isTextIn = true;

	public void ToggleTextIn() {
		if (isTextIn) {
			isTextIn = false;
		} else {
			isTextIn = true;
		}
		System.out.println(isTextIn);

	}

	// ブラシのサイズを変更
	public void ChangeBrushSize(int x) {
		this.brushSize = x;
	}

	// 未実装 RGBブラシ指定
	public void ChangeColorRGB(int r, int g, int b) {
		this.brushColor = new Color(r, g, b);
	}

	// ブラシの色を変える
	public Color ChangeColor(String str_color) {
		System.out.println("Setting Color to :" + str_color);

		this.brushColor = ColorMap.get(str_color);

		System.out.println("Now :" + this.brushColor);

		return this.brushColor;
	}

	// 画面リセット
	public void reset() {
		lo = new ArrayList<LineObject>();
		to = new ArrayList<TextObject>();
		repaint();

	}

	// 絵画を一つ削除 （ ctrl + z )
	public void pop() {
		lo.remove(lo.size() - 1);
	}

	// 絵画のリストのサイズを戻す
	public int getloSize() {
		int size = lo.size();
		return size;
	}

	// コンストラクタ
	public DrawWindow() {

		// プリセットブラシ色生成
		ColorMap.put("black", new Color(1, 10, 19));
		ColorMap.put("red", new Color(255, 50, 50));
		ColorMap.put("blue", new Color(53, 63, 188));
		ColorMap.put("green", new Color(0, 112, 66));
		ColorMap.put("yellow", new Color(200, 170, 110));
		ColorMap.put("orange", new Color(255, 153, 51));

		ChangeColor("red");

		// 画面を透明化
		setOpaque(false);

		// マウス入力リスナー
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					currentTextObj = new TextObject(e);
//					ToggleTextIn();
					System.out.println("isTextMode =" + isTextIn);

				} else {
					LineObject lineobj = new LineObject();
					lineobj.addlo(e.getPoint(), brushColor, brushSize);
					lo.add(lineobj);
				}

				repaint();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// 画面上の座標を保存
				lo.get(lo.size() - 1).addlo(e.getPoint(), brushColor, brushSize);

				repaint();
			}
		});

		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				// ホイール操作でテキストサイズの変更
				int notches = e.getWheelRotation();
				to.get(to.size() - 1).textSize += notches * 5;
				System.out.println("notches :" + notches);

				if (to.get(to.size() - 1).textSize > 101) {
					to.get(to.size() - 1).textSize = 100;
				} else if (to.get(to.size() - 1).textSize < -1) {
					to.get(to.size() - 1).textSize = 0;
				}
				repaint();
			}

		});

	}

//	private Color Color(int i, int j, int k) {
//		return Color(i, j, k);
//	}

	// 画面描画 repaint()により呼び出される
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 取得した座標を線で結んで描画
		for (LineObject lineObj : lo) {
			int i = -1;
			g2.setStroke(new BasicStroke(lineObj.brushSize));
			g2.setColor(lineObj.brushColor);
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

		for (TextObject t : to) {
			g2.setColor(t.textColor);
			ArrayList<Point> points = t.getTO();
			for (Point p : points) {

				g2.setFont(new Font("Arial", Font.PLAIN, t.textSize));
				g2.drawString(t.text, p.x, p.y);

				System.out.println("DrawFinished");
				System.out.println(t.text);
				System.out.println(t.textSize);
				System.out.println(p.x);
				System.out.println(p.y);
			}

		}

	}

}