import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
								Test(canvas);
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

	private static void Test(TestCanvas canvas){
		DrawObject sankaku = createRundomizeDrawObject(5, canvas);

		sankaku.setFlagSortPoint(true);
		sankaku.setFlagSetText(true);

		canvas.addDrawObject(sankaku);
		System.out.println("draw");
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