import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TestCanvas extends JPanel {
	//canvasの大きさを指定
	public static final int CANVAS_WIDTH_MAX = 600;
	public static final int CANVAS_HEIGHT_MAX = 400;

	//描画するPointの大きさ
	public static final int PointCircleSize = 10;

	public ArrayList<Point> points = new ArrayList<Point>(10);
	public ArrayList<DrawObject> objects = new ArrayList<DrawObject>();

	//canvasに描かれている点の個数
	public int nowPoint = 0;

	/**
	 * 指定の大きさでcanvasを生成
	 * @param width
	 * @param height
	 */
	public TestCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}

	public TestCanvas(){
		this(CANVAS_WIDTH_MAX, CANVAS_HEIGHT_MAX);
	}

	/**
	 * 一度画面を塗りつぶしてから描画する
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g.setColor(Color.white);
		g.fillRect(0, 0, CANVAS_WIDTH_MAX, CANVAS_HEIGHT_MAX);

		paintDrawObjects(g2);
	}

	/**
	 * Canvasに保存された複数のDrawObjectを
	 * 一つ取り出し、その中のpointを描きだす
	 * @param g2
	 */
	public void paintDrawObjects(Graphics2D g2){
		//書き出すたびに点の個数を初期化する
		nowPoint = 0;
		for(int i = 0; i < objects.size(); i++ ){
			//DrawObjectを取り出す
			DrawObject drawObject = objects.get(i);
			settingDrawObject(drawObject);

			int[] pointx = new int[drawObject.points.size()];
			int[] pointy = new int[drawObject.points.size()];

			for(int j = 0; j < drawObject.points.size(); j++){
				//drawObjectからPointを取り出し座標をとる
				Point point = drawObject.points.get(j);
				pointx[j] = point.x;
				pointy[j] = point.y;

				//TODO まじっくなんばー
				//Point描画
				g2.setColor(point.color);
				g2.drawOval(point.x - (PointCircleSize / 2), point.y - (PointCircleSize / 2),
								PointCircleSize, PointCircleSize);
				g2.drawString(point.text, point.x - PointCircleSize, point.y - PointCircleSize);
			}
			//Polygon描写
			g2.setColor(drawObject.color);
			g2.drawPolygon(pointx, pointy, drawObject.points.size());
		}
	}

	/**
	 * DrawObjectをcanvasに追加
	 * @param drawObject
	 */
	public void addDrawObject(DrawObject drawObject){
		this.objects.add(drawObject);
		repaint();
	}

	/**
	 * PointをDrawObjectに変換しcanvasに追加
	 * @param point
	 */
	public void addDrawObject(Point point){
		DrawObject drawObject = new DrawObject(point);
		addDrawObject(drawObject);
	}

	//TODO sortのタイミングをDrawObjectが生成された瞬間に変更
	//TODO addNowPointがよくわからんことになってる
	public void settingDrawObject(DrawObject drawObject){
		drawObject.sortDrawObject(drawObject);
		addNowPoint(drawObject.setText(getNowPoint()));

	}

	public void clear(){
		objects.clear();
		this.nowPoint = 0;
		repaint();
	}

	/**
	 * nowPointを進める
	 * @param i
	 */
	public void addNowPoint(int i){
		this.nowPoint += i;
	}

	/**
	 * nowPointを取得
	 * @return nowPoint
	 */
	public int getNowPoint(){
		return this.nowPoint;
	}
}