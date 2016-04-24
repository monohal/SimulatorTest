import java.awt.Color;

public class Point{
	int x;
	int y;
	String text;
	Color color;

	double angle;

	//TODO LineとColorの順番を統一したいなぁ
	/**
	* 座標を持った点を生成
	*/
	public Point(int x, int y, String text, Color color){
		this.x = x;
		this.y = y;
		this.text = text;
		this.color = color;
	}

	public Point(int x, int y){
		this(x, y, "", Color.black);
	}

	public Point(int x, int y, Color color){
		this(x, y, "", color);
	}

	public Point(int x, int y, String text){
		this(x, y, text, Color.black);
	}

	/**
	* 原点の座標を持った点を生成
	*/
	public Point(){
		this(0,0);
	}

	public int[] getPoint(){
		int n[] = {this.x, this.y};
		return n;
	}

	public String toString(){
		return "(" + this.x + ". " + this.y + ")";
	}

	public String getText(){
		return this.text;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public void setAngle(double angle){
		this.angle = angle;
	}

	public double getAngle(){
		return this.angle;
	}

}