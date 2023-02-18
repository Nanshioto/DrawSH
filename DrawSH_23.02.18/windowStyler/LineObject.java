package windowStyler;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class LineObject {
	ArrayList<Point> lineobj = new ArrayList<Point>();
	Color brushColor = Color.red;
	int brushSize = 5;

	public LineObject() {

	}

	public void addlo(Point p, Color c, int x) {
		lineobj.add(p);
		this.brushColor = c;
		this.brushSize = x;
	}

	public ArrayList<Point> getPoints() {
		return this.lineobj;
	}

}
