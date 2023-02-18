package windowStyler;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TextObject {
	ArrayList<Point> textobj = new ArrayList<Point>();
	String text = "";
	Color textColor = Color.red;
	int textSize = 100;
	

	public TextObject(MouseEvent e) {
		//adds text position
		this.textobj.add(e.getPoint());
	}


	public void addTO(Color c ,String x, int y) {
		System.out.println("added stuff to TO");
		this.text +=x;
		this.textColor = c;
		this.textSize = y;
	}

	public ArrayList<Point> getTO() {
		return this.textobj;
	}
	
	public void removeChar() {
		this.text = this.text.substring(0,this.text.length()-1);
	}

}
