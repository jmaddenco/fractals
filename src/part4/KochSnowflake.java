package part4;

import java.awt.geom.Point2D;
import draw.UltraDraw;

public class KochSnowflake {

	public KochSnowflake ()
	{
		UltraDraw.setXscale(0,512);
		UltraDraw.setYscale(0,512);
		UltraDraw.clear(UltraDraw.GRAY);
		UltraDraw.setPenColor(UltraDraw.WHITE);
	}
	
	public void draw (int n, Point2D.Double start, Point2D.Double end, boolean sideWays)
	{
		curve(start,end,n, sideWays);
		
	}
	
	public void curve (Point2D.Double start, Point2D.Double end, int n, boolean sideWays)
	{
		if (n <= 0) {
			return;
		}
		
		double width = Math.abs(start.x-end.x);
		
		double height = start.y-end.y;
		
		double distance = Math.sqrt(Math.pow(width, 2)+Math.pow(Math.abs(height), 2));
		
		double length = distance*(12.0/39.0);
		
		double baseLength = width*(58.0/342.0);
		
		double slope = 0;
		
		double perpSlope = 0;
		
		if(width!=0) {
			slope = -height/width;
		}
		if(height!=0) {
			perpSlope = -(width/height);
		}
		if (sideWays == false){
		
		Point2D.Double center = new Point2D.Double(start.x+width/2, start.y-height/2);
		Point2D.Double spike = new Point2D.Double(center.x,center.y+length);
		Point2D.Double point1 = new Point2D.Double(spike.x-baseLength, slope*(spike.x-baseLength-start.x)+start.y);
		Point2D.Double point2 = new Point2D.Double(spike.x+baseLength, slope*(spike.x+baseLength-start.x)+start.y);
		
		double spikeDist = length/Math.sqrt(1+Math.pow(slope, 2));
		
		if(slope < 0) {
			spike = new Point2D.Double(center.x+spikeDist, center.y- perpSlope*spikeDist);
		}
		
		if(slope > 0) {
			spike = new Point2D.Double(center.x-spikeDist, center.y+ perpSlope*spikeDist);
		}
		
		curve(start, point1, n-1,false);
		curve(point1, spike, n-1,false);
		curve(spike, point2, n-1, false);
		curve(point2, end, n-1, false);
		
		UltraDraw.setPenColor(UltraDraw.RED);
		UltraDraw.line(start.x, start.y, point1.x,point1.y);
		UltraDraw.line(point1.x, point1.y, spike.x, spike.y);
		UltraDraw.line(spike.x, spike.y, point2.x, point2.y);
		UltraDraw.line(point2.x, point2.y, end.x, end.y);
	}
		else {
			Point2D.Double center = new Point2D.Double(start.x+width/2, start.y-height/2);
			Point2D.Double spike = new Point2D.Double(center.x,center.y+length);
			Point2D.Double point1 = new Point2D.Double(spike.x-baseLength, slope*(spike.x-baseLength-start.x)+start.y);
			Point2D.Double point2 = new Point2D.Double(spike.x+baseLength, slope*(spike.x+baseLength-start.x)+start.y);
			
			double spikeDist = length/Math.sqrt(1+Math.pow(slope, 2));
			
			if(slope < 0) {
				spike = new Point2D.Double(center.x-spikeDist, center.y+perpSlope*spikeDist);
			}
			
			if(slope > 0) {
				spike = new Point2D.Double(center.x+spikeDist, center.y-perpSlope*spikeDist);
			}
			
			curve(start, point1, n-1,true);
			curve(point1, spike, n-1,true);
			curve(spike, point2, n-1, true);
			curve(point2, end, n-1, true);
			
			UltraDraw.setPenColor(UltraDraw.RED);
			UltraDraw.line(start.x, start.y, point1.x,point1.y);
			UltraDraw.line(point1.x, point1.y, spike.x, spike.y);
			UltraDraw.line(spike.x, spike.y, point2.x, point2.y);
			UltraDraw.line(point2.x, point2.y, end.x, end.y);
		}
		
		
	}
	
	
	
}