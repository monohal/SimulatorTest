import java.util.Comparator;

public class PointComparator implements Comparator<Point>{
	@Override
	public int compare(Point p1, Point p2){
		
		if(p1.angle < p2.angle){
			return -1;
		}else{
			return 1;
		}
	}
}
