package windowStyler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class windowStyler extends JPanel {
	private static final long serialVersionUID = -5445242313543305589L;

	private static Point ColorBtnSize = new Point(30, 30);
	private static Point BtnSize = new Point(100, 20);
	// スクショの格納パス
	static String scnShotPath = "";

	public static void main(String[] args) {

		// 保存先の選択 /
		chooseDir();

		// 保存パスにフォルダーを作成
		createDir();

		// 画面のサイズ取得
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle bounds = gs[0].getDefaultConfiguration().getBounds();

		// 絵画画面の生成
		JFrame frame = new JFrame();
		JPanel panel = new DrawWindow();

		frame.setUndecorated(true);
		frame.add(panel);
		frame.setSize(bounds.width, bounds.height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(0, 0, 0, 1));
		frame.setVisible(true);

		// コントロール画面の生成
		JFrame palette = new JFrame("Palette");
		palette.setUndecorated(false);
		palette.setLayout(new FlowLayout());
		palette.setSize(BtnSize.x, BtnSize.y * 16);
		palette.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		palette.setLocation(0, 0);
		palette.setAlwaysOnTop(true);

		// キーショートカット画面の生成
		JFrame shortcut = new JFrame("Shortcut");
		shortcut.setUndecorated(true);
		shortcut.setSize(500, 150);
		shortcut.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shortcut.setLocation(bounds.width - 500, bounds.height - 150);
		shortcut.setBackground(new Color(0, 0, 0, 1));
		shortcut.setAlwaysOnTop(true);

		JPanel shortcutp = new JPanel();
//		shortcutp.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		shortcutp.setBackground(new Color(0, 0, 0, 100));
		JLabel help_text = new JLabel("SHORTCUT:\n");
		JLabel help_text2 = new JLabel("CTRL + S : Screenshot \n");
		JLabel help_text3 = new JLabel("CTRL + Z : Undo Drawing");
		int help_textSize = 30;

		help_text.setFont(new Font("Arial", Font.PLAIN, help_textSize));
		help_text2.setFont(new Font("Arial", Font.PLAIN, help_textSize));
		help_text3.setFont(new Font("Arial", Font.PLAIN, help_textSize));

		help_text.setForeground(Color.white);
		help_text2.setForeground(Color.white);
		help_text3.setForeground(Color.white);

		shortcutp.add(help_text);
		shortcutp.add(help_text2);
		shortcutp.add(help_text3);

		shortcut.add(shortcutp);
		shortcut.setVisible(true);

		// プログラムの終了ボタン
		JButton exit = new JButton("Close");
		exit.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// 絵画画面のリセットボタン
		JButton reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 我不知道为什么
				((DrawWindow) panel).reset();
			}
		});

		// WIP 未実装
		// Requires Remake cannot select behind content 2/4
		// Disable Draw
		JButton tDraw = new JButton("Drawable");
		tDraw.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		tDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFocusable(false);
				frame.setFocusableWindowState(false);
			}
		});
//		palette.add(tDraw);

		// * ブラシの色を設定*//
		JButton black = new JButton();
		black.setBackground(((DrawWindow) panel).ChangeColor("black"));
		black.setForeground(Color.white);
		black.setPreferredSize(new Dimension(ColorBtnSize.x, ColorBtnSize.y));
		black.setFocusPainted(false);
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DrawWindow) panel).ChangeColor("black");
			}
		});
		JButton blue = new JButton();
		blue.setBackground(((DrawWindow) panel).ChangeColor("blue"));
		blue.setForeground(Color.white);
		blue.setPreferredSize(new Dimension(ColorBtnSize.x, ColorBtnSize.y));
		blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DrawWindow) panel).ChangeColor("blue");
			}
		});
		JButton red = new JButton();
		red.setBackground(((DrawWindow) panel).ChangeColor("red"));
		red.setForeground(Color.white);
		red.setPreferredSize(new Dimension(ColorBtnSize.x, ColorBtnSize.y));
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DrawWindow) panel).ChangeColor("red");
			}
		});
		JButton green = new JButton();
		green.setBackground(((DrawWindow) panel).ChangeColor("green"));
		green.setForeground(Color.white);
		green.setPreferredSize(new Dimension(ColorBtnSize.x, ColorBtnSize.y));
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DrawWindow) panel).ChangeColor("green");
			}
		});

		JButton yellow = new JButton();
		yellow.setBackground(((DrawWindow) panel).ChangeColor("yellow"));
		yellow.setForeground(Color.white);
		yellow.setPreferredSize(new Dimension(ColorBtnSize.x, ColorBtnSize.y));
		yellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DrawWindow) panel).ChangeColor("yellow");
			}
		});
		JButton orange = new JButton();
		orange.setBackground(((DrawWindow) panel).ChangeColor("orange"));
		orange.setForeground(Color.white);
		orange.setPreferredSize(new Dimension(ColorBtnSize.x, ColorBtnSize.y));
		orange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DrawWindow) panel).ChangeColor("orange");
			}
		});

		// 画面キャプチャボタン
		JButton scrnShot = new JButton("ScrnShot");
		scrnShot.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		scrnShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Robot robot = new Robot();
					Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
					Date date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

					ImageIO.write(screenFullImage, "png",
							new File(scnShotPath + "\\" + dateFormat.format(date) + ".png"));
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});

		// button font
//		Font font = scrnShot.getFont();
//		float size = font.getSize() - 5.0f;
//		scrnShot.setFont(font.deriveFont(size));

		// SET BRUSH SIZE
		JTextField inputField = new JTextField();
		inputField.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		palette.add(inputField);

		JButton setBrushSize = new JButton("SetSize");
		setBrushSize.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		palette.add(setBrushSize);

		setBrushSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = inputField.getText();

				((DrawWindow) panel).ChangeBrushSize(Integer.parseInt(input));
			}
		});

		// SET BRUSH RGB

		JButton setBrushRGB = new JButton("SetColor");
		setBrushRGB.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		palette.add(setBrushRGB);

		setBrushRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame RGBselect = new JFrame("RGB_SELECT");
				RGBselect.setUndecorated(false);
				RGBselect.setLayout(new FlowLayout());
				RGBselect.setSize(600, 400);
				RGBselect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				RGBselect.setLocation(bounds.width / 2, bounds.height / 2);
				RGBselect.setAlwaysOnTop(true);

				JTextField r = new JTextField();
				JTextField g = new JTextField();
				JTextField b = new JTextField();
				r.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
				g.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
				b.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
				RGBselect.add(r);
				RGBselect.add(g);
				RGBselect.add(b);

				JButton setColor = new JButton("SET COLOR");
				RGBselect.add(setColor);
				setColor.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
				setColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int ri = Integer.parseInt(r.getText());
						int gi = Integer.parseInt(g.getText());
						int bi = Integer.parseInt(b.getText());

						((DrawWindow) panel).ChangeColorRGB(ri, gi, bi);

						RGBselect.dispose();
					}
				});

				RGBselect.setVisible(true);

			}
		});

		// Select Scrnshot file save location
		JButton folderChooser = new JButton("Save Location");
		folderChooser.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		folderChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseDir();
				createDir();
			}
		});

		// Draw Text
		JButton text = new JButton("Text");
		text.setPreferredSize(new Dimension(BtnSize.x, BtnSize.y));
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Text Draw Test
				((DrawWindow) panel).ToggleTextIn();

				TextInputListener til = new TextInputListener((DrawWindow) panel);
				frame.addKeyListener(til);
			}
		});

		palette.add(folderChooser);
		palette.add(text);
		palette.add(black);
		palette.add(red);
		palette.add(blue);
		palette.add(green);
		palette.add(yellow);
		palette.add(orange);
		palette.add(scrnShot);
		palette.add(reset);
		palette.add(exit);
		palette.setVisible(true);

		// ショートカットリスナーの設定
		ShortcutListener sl = new ShortcutListener((DrawWindow) panel);
		frame.addKeyListener(sl);

//		JSlider slider = new JSlider(JSlider.VERTICAL, 5, 45, 10);
//		slider.setMajorTickSpacing(20);
//		slider.setPreferredSize(new Dimension(50, 50));
//		slider.setPaintTicks(true);
//		slider.setPaintLabels(true);
//
//		palette.add(slider, BorderLayout.NORTH);

		palette.setVisible(true);
	}

	public static void chooseDir() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			scnShotPath = selectedFile.getAbsolutePath();
			System.out.println(scnShotPath);
		}
	}

	public static void createDir() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();

		String FP = scnShotPath + "\\DrawSH-" + dateFormat.format(date);
		File directory = new File(FP);
		boolean success = directory.mkdir();
		if (success) {
			System.out.println("Directory created successfully");
		} else {
			System.out.println("Failed to create directory");
		}
		scnShotPath = FP;
	}
}
