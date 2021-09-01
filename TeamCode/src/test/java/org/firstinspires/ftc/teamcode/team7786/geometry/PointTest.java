package org.firstinspires.ftc.teamcode.team7786.geometry;

import static org.junit.Assert.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import org.junit.Test;

public class PointTest {
    //tests for addToPoint
    @Test
    public void addToPointBasic(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point sum = new Point(3, 3);
        assertEquals(p1.addToPoint(p2).x, p2.addToPoint(p1).x, 0);
        assertEquals(p1.addToPoint(p2).y, p2.addToPoint(p1).y, 0);
        assertEquals(p1.addToPoint(p2).x, sum.x, 0);
        assertEquals(p1.addToPoint(p2).y, sum.y, 0);
    }
    @Test
    public void addToPointZero(){
        Point p1 = new Point(0, 1);
        Point p2 = new Point(2, 0);
        Point sum = new Point(2, 1);
        assertEquals(p1.addToPoint(p2).x, p2.addToPoint(p1).x, 0);
        assertEquals(p1.addToPoint(p2).y, p2.addToPoint(p1).y, 0);
        assertEquals(p1.addToPoint(p2).x, sum.x, 0);
        assertEquals(p1.addToPoint(p2).y, sum.y, 0);
    }
    @Test
    public void addToPointOneNegative(){
        Point p1 = new Point(1, -1);
        Point p2 = new Point(2, 2);
        Point sum = new Point(3, 1);
        assertEquals(p1.addToPoint(p2).x, p2.addToPoint(p1).x, 0);
        assertEquals(p1.addToPoint(p2).y, p2.addToPoint(p1).y, 0);
        assertEquals(p1.addToPoint(p2).x, sum.x, 0);
        assertEquals(p1.addToPoint(p2).y, sum.y, 0);
    }
    @Test
    public void addToPointBothNegative(){
        Point p1 = new Point(1, -1);
        Point p2 = new Point(2, -2);
        Point sum = new Point(3, -3);
        assertEquals(p1.addToPoint(p2).x, p2.addToPoint(p1).x, 0);
        assertEquals(p1.addToPoint(p2).y, p2.addToPoint(p1).y, 0);
        assertEquals(p1.addToPoint(p2).x, sum.x, 0);
        assertEquals(p1.addToPoint(p2).y, sum.y, 0);
    }
    //tests for subtractPoint
    @Test
    public void subtractPointBasic(){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 1);
        Point difference = new Point(1, 1);
        assertEquals(p1.subtractPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractPoint(p2).y, difference.y, 0);
    }
    @Test
    public void subtractPointZero(){
        Point p1 = new Point(0, 2);
        Point p2 = new Point(1, 0);
        Point difference = new Point(-1, 2);
        assertEquals(p1.subtractPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractPoint(p2).y, difference.y, 0);
    }
    @Test
    public void subtractPointOneNegative(){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, -1);
        Point difference = new Point(1, 3);
        assertEquals(p1.subtractPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractPoint(p2).y, difference.y, 0);
    }
    @Test
    public void subtractPointBothNegative(){
        Point p1 = new Point(-2, -2);
        Point p2 = new Point(-1, -3);
        Point difference = new Point(-1, 1);
        assertEquals(p1.subtractPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractPoint(p2).y, difference.y, 0);
    }
    //tests for subtractFromPoint
    @Test
    public void subtractFromPointBasic(){
        Point p2 = new Point(2, 2);
        Point p1 = new Point(1, 1);
        Point difference = new Point(1, 1);
        assertEquals(p1.subtractFromPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractFromPoint(p2).y, difference.y, 0);
    }
    @Test
    public void subtractFromPointZero(){
        Point p2 = new Point(0, 2);
        Point p1 = new Point(1, 0);
        Point difference = new Point(-1, 2);
        assertEquals(p1.subtractFromPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractFromPoint(p2).y, difference.y, 0);
    }
    @Test
    public void subtractFromPointOneNegative(){
        Point p2 = new Point(2, 2);
        Point p1 = new Point(1, -1);
        Point difference = new Point(1, 3);
        assertEquals(p1.subtractFromPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractFromPoint(p2).y, difference.y, 0);
    }
    @Test
    public void subtractFromPointBothNegative(){
        Point p2 = new Point(-2, -2);
        Point p1 = new Point(-1, -3);
        Point difference = new Point(-1, 1);
        assertEquals(p1.subtractFromPoint(p2).x, difference.x, 0);
        assertEquals(p1.subtractFromPoint(p2).y, difference.y, 0);
    }
    //tests for multiplyScaler
    @Test
    public void multiplyScalarBase(){
        Point p1 = new Point(1, 1);
        double scaler = 2;
        Point p2 = new Point(2, 2);
        assertEquals(p1.multiplyScalar(scaler).x, p2.x, 0);
        assertEquals(p1.multiplyScalar(scaler).y, p2.y, 0);
    }
    @Test
    public void multiplyScalarZeroScaler(){
        Point p1 = new Point(1, 1);
        double scaler = 0;
        Point p2 = new Point(0, 0);
        assertEquals(p1.multiplyScalar(scaler).x, p2.x, 0);
        assertEquals(p1.multiplyScalar(scaler).y, p2.y, 0);
    }
    @Test
    public void multiplyScalarZeroPoint(){
        Point p1 = new Point(0, 1);
        double scaler = 2;
        Point p2 = new Point(0, 2);
        assertEquals(p1.multiplyScalar(scaler).x, p2.x, 0);
        assertEquals(p1.multiplyScalar(scaler).y, p2.y, 0);
    }
    @Test
    public void multiplyScalarNegativeScaler(){
        Point p1 = new Point(1, 1);
        double scaler = -2;
        Point p2 = new Point(-2, -2);
        assertEquals(p1.multiplyScalar(scaler).x, p2.x, 0);
        assertEquals(p1.multiplyScalar(scaler).y, p2.y, 0);
    }
    @Test
    public void multiplyScalarNegativePoint(){
        Point p1 = new Point(-1, 1);
        double scaler = 2;
        Point p2 = new Point(-2, 2);
        assertEquals(p1.multiplyScalar(scaler).x, p2.x, 0);
        assertEquals(p1.multiplyScalar(scaler).y, p2.y, 0);

    }
    //tests for distToPoint
    @Test
    public void distToPointBase(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        assertEquals(p1.distToPoint(p2), sqrt(2), 0.001);
    }
    @Test
    public void distToPointBackwards(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        assertEquals(p2.distToPoint(p1), sqrt(2), 0.001);
    }
    @Test
    public void distToPointNegative(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(-1, -1);
        assertEquals(p1.distToPoint(p2), sqrt(8), 0.001);
    }
    @Test
    public void distToPointZero(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(0, 0);
        assertEquals(p1.distToPoint(p2), sqrt(2), 0.001);
    }
    @Test
    public void distToPointHorizontal(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(0, 1);
        assertEquals(p1.distToPoint(p2), sqrt(1), 0.001);
    }
    @Test
    public void distToPointVertical(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 0);
        assertEquals(p1.distToPoint(p2), sqrt(1), 0.001);
    }
    @Test
    public void distToPointBothNegative(){
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(-2, -2);
        assertEquals(p1.distToPoint(p2), sqrt(2), 0.001);
    }
    @Test
    public void distToPointDecimal(){
        Point p1 = new Point(1.5, 1.5);
        Point p2 = new Point(0.5, 0.5);
        assertEquals(p1.distToPoint(p2), sqrt(2), 0.001);
    }
    @Test
    public void distToPointDistanceDecimal(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1.5, 1.5);
        assertEquals(p1.distToPoint(p2), sqrt(0.5), 0.001);
    }
}