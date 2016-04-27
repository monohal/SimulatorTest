/**
 * 演算系をまとめた
 * 全てstaticなメソッド
 */
public class Ruler{
	/**
	 * p1とp2間の距離を測る
	 * @param p1
	 * @param p2
	 * @return 距離
	 */
	public static double getDistance(Point p1, Point p2){
		double x = p1.x - p2.x;
		double y = p1.y - p2.y;

		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * 角度p1,p2,p3を計算する
	 * ここではp2の角が返される
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return 角度
	 */
	public static double getAngle(Point p1, Point p2, Point p3){
		double vec12[] ={p1.x - p2.x,  p1.y - p2.y };
		double vec23[] ={p3.x - p2.x,  p3.y - p2.y };

		double vec1223 = vec12[0] * vec23[0] + vec12[1] * vec23[1];
		double absvec12 = Math.sqrt(Math.pow(vec12[0], 2) + Math.pow(vec12[1], 2));
		double absvec23 = Math.sqrt(Math.pow(vec23[0], 2) + Math.pow(vec23[1], 2));

		return Math.toDegrees(Math.acos(vec1223 / (absvec12 * absvec23)));
	}

	/**
	 * 点1から点2までのをn:iの比率で内分する点を返す
	 * @param point1 点1
	 * @param point2 点2
	 * @param n
	 * @param i
	 * @return Point
	 */
	public static Point InteriorDivision(Point point1, Point point2,int n, int  i){
		// vecI = vecB * p + vecC * q
		double dis_12 = Ruler.getDistance(point1, point2);
		double dis_i = (dis_12 / n) * i;

		double p = (dis_12 - dis_i) / dis_12;
		double q = 1 - p;

		double vecI_X  = (point1.x * p) + (point2.x * q);
		double vecI_Y  = (point1.y * p) + (point2.y * q);

		Point iPoint = new Point((int)vecI_X, (int)vecI_Y);
		return iPoint;
	}
}