import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimulatorTest{

	private final static void createAndShowGUI(){
		//↓↓レイアウト設定↓↓
		JFrame mainFrame = new JFrame("SimulatorTest");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		{
			final TestCanvas canvas = new TestCanvas();

			//Canvas CENTER
			panel.add(canvas, BorderLayout.CENTER);

			//buttonPanel SOUTH
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			{
				//TestAボタン
				JButton btnTestA = new JButton("解法1");
				btnTestA.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent event){
								Test(canvas);
							}
						});
				buttonPanel.add(btnTestA);

				//TestBボタン
				JButton btnTestB = new JButton("解法2");
				btnTestB.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent event){
								TestB(canvas);
							}
						});
				buttonPanel.add(btnTestB);

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


			//TODO オブジェクト変数で頑張る
			//Canvas EAST
			/*
			JPanel viewPanel = new JPanel();
			{
				viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.PAGE_AXIS));

				JLabel test = new JLabel("TEST");
				test.setPreferredSize(new Dimension(100, 100));
				viewPanel.add(test);
				viewPanel.add(new JLabel("A"));
				viewPanel.add(new JLabel("B"));
				viewPanel.add(new JLabel("C"));
			}
			panel.add(viewPanel, BorderLayout.EAST);
			*/
		}
		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		//↑↑レイアウト設定↑↑
	}

	private static void Test(TestCanvas canvas){
		DrawObject sankaku = createRundomizeDrawObject(3, canvas);

		sankaku.setFlagSortPoint(true);
		sankaku.setFlagSetText(true);

		canvas.addDrawObject(sankaku);
	}

	private static void TestB(TestCanvas canvas){

		ArrayList<Point> san = new ArrayList<Point>();
		san.add(new Point(250, 50));
		san.add(new Point(50, 200));
		san.add(new Point(450, 300));

		//DrawObject sankaku = createRundomizeDrawObject(3, canvas);
		DrawObject sankaku = new DrawObject(san);

		ArrayList<Point> points = sankaku.getPoint();

		sankaku.setFlagSortPoint(true);
		sankaku.setFlagSetText(true);

		canvas.addDrawObject(sankaku);

		//原点を作成
		Point oPoint = new Point();
		oPoint.setText("O");
		canvas.addDrawObject(oPoint);

		//セッティング
		Point xPoint = null;
		double Lmin = Double.MAX_VALUE;
		double angleAXB;
		double angleBXC;
		double angleCXA;
		Point pointA = points.get(0);
		Point pointB = points.get(1);
		Point pointC = points.get(2);

		//試行回数
		int count = 100;

		for(int i = 0; i < count; i++){
			double p = Math.random();
			double q = Math.random();
			double r = Math.random();

			//正規化
			double fact = 1 / (p + q + r);
			p = p * fact;
			q = q * fact;
			r = r * fact;

			double randomX = (pointA.x - oPoint.x) * p
					+(pointB.x - oPoint.x) * q + (pointC.x - oPoint.x) * r;

			double randomY = (pointA.y - oPoint.y) * p
					+(pointB.y - oPoint.y) * q + (pointC.y - oPoint.y) * r;

			//三角形内にランダムな点を打つ
			Point randomPoint = new Point ((int)randomX, (int)randomY);
			canvas.addDrawObject(new DrawObject(randomPoint));

			//距離を測って最小値を更新する
			double distance = Ruler.getDistance(pointA, randomPoint)
					+ Ruler.getDistance(pointB, randomPoint)
					+ Ruler.getDistance(pointC, randomPoint);
			if(distance < Lmin){
				Lmin = distance;
				xPoint = randomPoint;
			}
		}

		//最後に角度を取る
		angleAXB = Ruler.getAngle(pointA, xPoint, pointB);
		angleBXC = Ruler.getAngle(pointB, xPoint, pointC);
		angleCXA = Ruler.getAngle(pointC, xPoint, pointA);

		xPoint.color = Color.red;
		canvas.addDrawObject(xPoint);

		//ファイル操作 CSVファイルで出力

		try{
			File fl = new File("./test.csv");

			if(!fl.exists()){
				fl.createNewFile();

				FileWriter fw = new FileWriter(fl);
				fw.write( "xPoint" + ",");
				fw.write( "Lmin" + ",");
				fw.write( "AXB" + ",");
				fw.write( "BXC" + ",");
				fw.write( "CXA" + "," + "\n");
				fw.close();
			}

			FileWriter fw = new FileWriter(fl, true);
			fw.write( xPoint + ",");
			fw.write( Lmin + ",");
			fw.write( angleAXB + ",");
			fw.write( angleBXC + ",");
			fw.write( angleCXA + "," + "\n");
			fw.close();

		}catch(IOException e){
			System.out.println(e + "例外が発生しました");
		}




	}

	private static DrawObject createRundomizeDrawObject(int n, TestCanvas canvas){
		ArrayList<Point> points = new ArrayList<>();
		Random rnd = new Random();
		for(int i = 0; i < n; i++){
			points.add(new Point(rnd.nextInt(canvas.getWidth()), rnd.nextInt(canvas.getHeight())));
		}
		return new DrawObject(points);
	}

	public static void main(String[] args) {
		//SimulatorTest frame = new SimulatorTest();
		createAndShowGUI();
	}
}