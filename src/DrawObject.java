import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class DrawObject{
	ArrayList<Point> points;
	//ArrayList<String> texts;

	Boolean flagSetText = false;
	Boolean flagSortPoint = true;

	Color color;

	public DrawObject(Point p){
		ArrayList<Point> ps = new ArrayList<Point>();
		ps.add(p);
		this.points = ps;
	}

	public DrawObject(ArrayList<Point> ps){
		this.points = ps;
	}

	public DrawObject(int x, int y){
		this(new Point(x, y));
	}

	public DrawObject(int[] x, int[] y){
		for(int i = 0; i < x.length; i++){
			addPoint(x[i], y[i]);
		}
	}

	public DrawObject(Integer[] x, Integer[] y){
		for(int i = 0; i < x.length; i++){
			addPoint(x[i], y[i]);
		}
	}

	/*
	//TODO 例外処理
	//System.out.printf("DrawObjectの引数x座標とy座標の数が一致しません\n");
	public DrawObject(List<Integer> listx, List<Integer> listy){
		Integer[] x =(Integer[])listx.toArray(new Integer[0]);
		Integer[] y =(Integer[])listy.toArray(new Integer[0]);
		this(x, y);
	}
	*/

	public void addPoint(int x, int y){
		points.add(new Point(x, y));
	}


	//TODO フォーマットの作成
	//TODO Point の数とテキストの数が一致しないときにはどうなるんです
	/**
	 * NowPointだけずらして点にラベル(AtoZ)をつける
	 * flagSetTextがtrueの時だけ実行される
	 * @param NowPoint 何個目の点か()
	 */
	public int setText(int NowPoint){
		int i = 0;
		if(flagSetText){
			for(char n = (char)('A' + NowPoint); n < (char)('A' + points.size() + NowPoint); n++){
				Point point = points.get(i);
				point.setText("点" + n);
				i++;
			}
		}
		return i;
	}

	/**
	 * 指定したtextのArrayListをpointにセットする
	 * @param texts
	 */
	public void setText(ArrayList<String> texts){
		int i = 0;

		for(Point point : points){
			point.setText(texts.get(i));
			i++;
		}
	}

	/**
	 * ポイントにテキストにセットする
	 * @param text
	 */
	public void setText(String text){
		ArrayList<String> texts = new ArrayList<>();
		texts.add(text);

		setText(texts);
	}


	/**
	 * 色をセットする
	 * @param color
	 */
	public void setColor(Color color){
		this.color = color;
	}

	/**
	 * 閉じた多角形にするためには線をつなぐ順番を考慮する必要がある
	 * 手順
	 * 1.yが最低値のPoint p0 をサーチする
	 * 2.p0と同じ高さに仮想のPoint pVirtual を生成する
	 * 3.pViertual, p0, 他のPoint の3点で角度で測り、昇順にソートする
	 * @param drawObject
	 */
	public void sortDrawObject(DrawObject drawObject){
		//1. yが最低値のPointを基準点とする
		if(flagSortPoint){
			Point p0 = null;
			int miny = Integer.MAX_VALUE;

			for(Point p : drawObject.points){
				if(miny > p.y){
					miny = p.y;
					p0 = p;
				}
			}

			//2. 角度を測るための仮想の点を生成
			Point pVirtual = new Point(0,miny);

			//3. 角度を測り、anglesに入れる
			for(Point p : drawObject.points){
				double angle = Ruler.getAngle(pVirtual, p0, p);

				//p = p0 の場合NuNが返るため
				if(Double.isNaN(angle)){
					p.setAngle(0);
				}else{
					p.setAngle(angle);
				}
			}
			//anglesでソートする
			Collections.sort(drawObject.points, new PointComparator());
		}
	}

	/**
	 * pointにtextを自動的にセットするかどうか
	 * 初期値 : false
	 * @param flag
	 */
	public void setFlagSetText(boolean flag){
		this.flagSetText = flag;
	}

	/**
	 * DrawObjectのpointをソートするかどうか
	 * 初期値 : true
	 * @param flag
	 */
	public void setFlagSortPoint(boolean flag){
		this.flagSortPoint = flag;
	}

	/**
	 * DrawObjectのpointsを返す
	 * @return points
	 */
	public ArrayList<Point> getPoint(){
		return this.points;
	}

	/**
	 * pointsの中身を消去
	 */
	public void clear(){
		points.clear();
	}

}