package windowStyler;

import java.awt.Point;
import java.util.ArrayList;

public class LineObject {
	ArrayList<Point> lineobj = new ArrayList<Point>();
	public LineObject() {
		
	}
	
	public void addlo(Point p) {
		lineobj.add(p);
	}
	
	public ArrayList<Point> getPoints(){
		return this.lineobj;
	}

}
