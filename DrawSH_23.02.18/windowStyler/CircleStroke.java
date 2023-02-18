package windowStyler;

import java.awt.*;
import java.awt.geom.*;

public class CircleStroke implements Stroke {
	private float diameter;

	public CircleStroke(float diameter) {
		this.diameter = diameter;
	}

	@Override
	public Shape createStrokedShape(Shape shape) {
		float radius = diameter / 2;
		GeneralPath path = new GeneralPath();
		PathIterator iterator = shape.getPathIterator(null);
		float[] points = new float[6];
		float moveX = 0, moveY = 0;
		float lastX = 0, lastY = 0;
		float thisX = 0, thisY = 0;
		int type = 0;
		float next = 0;
		int steps = 0;
		while (!iterator.isDone()) {
			type = iterator.currentSegment(points);
			switch (type) {
			case PathIterator.SEG_MOVETO:
				moveX = lastX = points[0];
				moveY = lastY = points[1];
				path.moveTo(moveX + radius, moveY);
				next = 0;
				steps = 0;
				break;
			case PathIterator.SEG_CLOSE:
				points[0] = moveX;
				points[1] = moveY;
			case PathIterator.SEG_LINETO:
				thisX = points[0];
				thisY = points[1];
				float dx = thisX - lastX;
				float dy = thisY - lastY;
				float distance = (float) Math.sqrt(dx * dx + dy * dy);
				if (distance >= radius) {
					float r = 1.0f / distance;
					float angle = (float) Math.atan2(dy, dx);
					while (steps < distance / radius) {
						float x = lastX + next * dx;
						float y = lastY + next * dy;
						path.append(new Arc2D.Float(x - radius, y - radius, diameter, diameter, 0, 360, Arc2D.OPEN),
								true);
						next += r * radius;
						steps++;
					}
				}
				lastX = thisX;
				lastY = thisY;
				break;
			}
			iterator.next();
		}
		return path;
	}
}