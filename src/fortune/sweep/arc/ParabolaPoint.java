package fortune.sweep.arc;

import fortune.sweep.geometry.Point;

// Decompiled by Jad v1.5.7c. Copyright 1997-99 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packfields(5) packimports(3) nocasts braces 
// Source File Name:   Fortune.java

public class ParabolaPoint extends Point
{

	double a, b, c;

	public ParabolaPoint(Point point)
	{
		super(point);
	}

	public double realX()
	{
		return y;
	}

	public double realY(double d)
	{
		return d - x;
	}

	public CirclePoint calculateCenter(Point point, ArcNode arcnode,
			Point point1)
	{
		CirclePoint circlepoint = null;
		Point point2 = new Point(arcnode.x - point.x, arcnode.y - point.y);
		Point point3 = new Point(point1.x - arcnode.x, point1.y
				- arcnode.y);
		if (point3.y * point2.x > point3.x * point2.y) {
			double d = -point2.x / point2.y;
			double d1 = (point.y + point2.y / 2D) - d
					* (point.x + point2.x / 2D);
			double d2 = -point3.x / point3.y;
			double d3 = (arcnode.y + point3.y / 2D) - d2
					* (arcnode.x + point3.x / 2D);
			double d4;
			double d5;
			if (point2.y == 0.0D) {
				d4 = point.x + point2.x / 2D;
				d5 = d2 * d4 + d3;
			} else if (point3.y == 0.0D) {
				d4 = arcnode.x + point3.x / 2D;
				d5 = d * d4 + d1;
			} else {
				d4 = (d3 - d1) / (d - d2);
				d5 = d * d4 + d1;
			}
			circlepoint = new CirclePoint(d4, d5, arcnode);
		}
		return circlepoint;
	}

	public void init(double d)
	{
		double d1 = realX();
		double d2 = realY(d);
		a = 1.0D / (2D * d2);
		b = -d1 / d2;
		c = (d1 * d1) / (2D * d2) + d2 / 2D;
	}

	public double F(double d)
	{
		return (a * d + b) * d + c;
	}

	public double[] solveQuadratic(double d, double d1, double d2)
			throws Throwable
	{
		double ad[] = new double[2];
		double d3 = d1 * d1 - 4D * d * d2;
		if (d3 < 0.0D) {
			throw new Throwable();
		}
		if (d == 0.0D) {
			if (d1 != 0.0D) {
				ad[0] = -d2 / d1;
			} else {
				throw new Throwable();
			}
		} else {
			double d4 = Math.sqrt(d3);
			double d5 = -d1;
			double d6 = 2D * d;
			ad[0] = (d5 + d4) / d6;
			ad[1] = (d5 - d4) / d6;
		}
		return ad;
	}

}