import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TestCanvas extends JPanel {
	public static final int CANVAS_WIDTH_MAX = 600;
	public static final int CANVAS_HEIGHT_MAX = 400;

	public ArrayList<Point> points = new ArrayList<Point>(10);
	public ArrayList<DrawObject> objects = new ArrayList<DrawObject>();

	public int nowPoint = 0;


	public TestCanvas(int width, int height) {
		// Canvasのサイズ設定と生成
		setPreferredSize(new Dimension(width, height));
	}

	public TestCanvas(){
		this(CANVAS_WIDTH_MAX, CANVAS_HEIGHT_MAX);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g.setColor(Color.white);
		g.fillRect(0, 0, CANVAS_WIDTH_MAX, CANVAS_HEIGHT_MAX);
		System.out.printf("paint\n");

		paintDrawObjects(g2);
	}

	public void paintDrawObjects(Graphics2D g2){

		//書き出すたびに点の個数を初期化する
		nowPoint = 0;

		System.out.printf("size_%d\n", objects.size());
		for(int i = 0; i < objects.size(); i++ ){
			DrawObject drawObject = objects.get(i);
			settingDrawObject(drawObject);

			int[] pointx = new int[drawObject.points.size()];
			int[] pointy = new int[drawObject.points.size()];

			for(int j = 0; j < drawObject.points.size(); j++){
				Point point = drawObject.points.get(j);
				pointx[j] = point.x;
				pointy[j] = point.y;

				//Polygon描写
				g2.setColor(point.color);
				g2.drawRect(point.x, point.y, 3, 3);
				g2.drawString(point.text, point.x - 10, point.y - 10);
			}
			//Point描写
			g2.setColor(drawObject.color);
			g2.drawPolygon(pointx, pointy, drawObject.points.size());
		}
	}

	public void addDrawObject(DrawObject drawObject){
		this.objects.add(drawObject);
	}

	public void show(){
		repaint();
	}

	public void settingDrawObject(DrawObject drawObject){
		drawObject.sortDrawObject(drawObject);
		addNowPoint(drawObject.setText(getNowPoint()));

	}

	public void clear(){
		objects.clear();
		this.nowPoint = 0;
		show();
	}

	public void addNowPoint(int i){
		this.nowPoint += i;
	}

	public int getNowPoint(){
		return this.nowPoint;
	}
}