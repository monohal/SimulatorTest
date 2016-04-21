import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimulatorTest extends JFrame {
	public final TestCanvas canvas;
	public JPanel p;
	public JButton btnTest;
	public JButton btnClear;
	public Pen pen;

	private final static void createAndShowGUI(){
		//↓↓レイアウト設定↓↓
		JFrame mainFrame = new JFrame("SimulatorTest");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		{
			//Canvas CENTER
			final TestCanvas canvas = new TestCanvas();
			panel.add(canvas, BorderLayout.CENTER);

			//buttonPanel SOUTH
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			{
				//Testボタン
				JButton btnTest = new JButton("TEST");
				btnTest.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent event){
								//Test(pen);
							}
						});
				buttonPanel.add(btnTest);

				//Clearボタン
				JButton btnClear = new JButton("CLEAR");
				btnClear.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent event){
								System.out.printf("clear");
								canvas.clear();
							}
						});
				buttonPanel.add(btnClear);
			}
			panel.add(buttonPanel, BorderLayout.SOUTH);
		}

		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		//↑↑レイアウト設定↑↑
	}

	public SimulatorTest() {
		//オブジェクトの生成
		p = new JPanel();
		btnTest = new JButton("TEST");
		btnClear = new JButton("CLEAR");
		canvas = new TestCanvas();

		// タイトルを設定
		setTitle("SimulatorTest");

		//レイアウトの設定
		p.setLayout(null);
		canvas.setBounds(0, 0, TestCanvas.CANVAS_WIDTH_MAX, TestCanvas.CANVAS_HEIGHT_MAX);
		canvas.setBackground(Color.white);
		btnTest.setBounds(canvas.getWidth()/ 2 - 40, canvas.getHeight(), 80, 30);
		btnClear.setBounds(canvas.getWidth() / 2 - 40 + 100, canvas.getHeight(), 80, 30);
		setSize(canvas.getWidth(), canvas.getHeight() + 100);

		//メインパネルを作成してフレームに追加
		p.add(canvas);
		p.add(btnTest);
		p.add(btnClear);
		getContentPane().add(p, BorderLayout.CENTER);

		setVisible(true);
		//↑↑↑↑レイアウト設定終了↑↑↑↑

		pen = new Pen(canvas);

		//ボタンの設定
		btnTest.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					Test(pen);
				}
			}
		);

		btnClear.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					System.out.printf("clear");
					canvas.clear();
				}
			}
		);

	}

	public static void Test(Pen pen){

		Random rnd = new Random();

		ArrayList<Point> points = new ArrayList<Point>();
		Point p1 = new Point(rnd.nextInt(pen.canvas.getWidth()), rnd.nextInt(pen.canvas.getHeight()));
		Point p2 = new Point(rnd.nextInt(pen.canvas.getWidth()), rnd.nextInt(pen.canvas.getHeight()));
		Point p3 = new Point(rnd.nextInt(pen.canvas.getWidth()), rnd.nextInt(pen.canvas.getHeight()));
		Point p4 = new Point(rnd.nextInt(pen.canvas.getWidth()), rnd.nextInt(pen.canvas.getHeight()));

		points.add(p1);
		points.add(p2);
		points.add(p3);

		DrawObject sankaku = new DrawObject(points);
		DrawObject point = new DrawObject(p4);

		sankaku.setFlagSortPoint(true);
		sankaku.setFlagSetText(true);
		point.setFlagSetText(true);

		pen.Draw(sankaku);
		pen.Draw(point);

		System.out.printf("%.1f度\n",Ruler.getAngle(p1, p2, p3));
	}

	public static void main(String[] args) {
		//SimulatorTest frame = new SimulatorTest();
		createAndShowGUI();
	}
}