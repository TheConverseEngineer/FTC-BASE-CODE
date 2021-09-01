package org.firstinspires.ftc.teamcode.team7786.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {
    //tests for getClosestPoint
    @Test
    public void getClosestPoint1(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(5,3);
        Point p3 = new Point(4,7);
        Point finalPoint = new Point(4, 3);
        Point calcedPoint = Graph.getClosestPoint(p1, p2, p3);
        assertEquals(calcedPoint.x, finalPoint.x ,0);
        assertEquals(calcedPoint.y, finalPoint.y, 0);
    }
    @Test
    public void getClosestPointNegatives(){
        Point p1 = new Point(-2,3);
        Point p2 = new Point(-5,3);
        Point p3 = new Point(-4,7);
        Point finalPoint = new Point(-4, 3);
        Point calcedPoint = Graph.getClosestPoint(p1, p2, p3);
        assertEquals(calcedPoint.x, finalPoint.x ,0);
        assertEquals(calcedPoint.y, finalPoint.y, 0);
    }
    @Test
    public void getClosestPointAllNegative(){
        Point p1 = new Point(-2,-3);
        Point p2 = new Point(-5,-3);
        Point p3 = new Point(-4,-7);
        Point finalPoint = new Point(-4, -3);
        Point calcedPoint = Graph.getClosestPoint(p1, p2, p3);
        assertEquals(calcedPoint.x, finalPoint.x ,0);
        assertEquals(calcedPoint.y, finalPoint.y, 0);
    }
    @Test
    public void getClosestPointSomeNegative(){
        Point p1 = new Point(5,5);
        Point p2 = new Point(5,-5);
        Point p3 = new Point(-8,-1);
        Point finalPoint = new Point(5, -1);
        Point calcedPoint = Graph.getClosestPoint(p1, p2, p3);
        assertEquals(calcedPoint.x, finalPoint.x ,0);
        assertEquals(calcedPoint.y, finalPoint.y, 0);
    }
    /* These two will not be work atm gotta take a look at these
    @Test
    public void getClosestPointAlternatingNegatives(){
        Point p1 = new Point(-3,4);
        Point p2 = new Point(5,-4);
        Point p3 = new Point(-2,3);
        Point finalPoint = new Point(-1, 2);
        Point calcedPoint = Graph.getClosestPoint(p1, p2, p3);
        assertEquals(calcedPoint.x, finalPoint.x ,0);
        assertEquals(calcedPoint.y, finalPoint.y, 0);
    }
    @Test
    public void getClosestPoint6(){
        Point p1 = new Point(-3,4);
        Point p2 = new Point(5,-4);
        Point p3 = new Point(-2,3);
        Point finalPoint = new Point(-5, 2);
        Point calcedPoint = Graph.getClosestPoint(p1, p2, p3);
        assertEquals(calcedPoint.x, finalPoint.x ,0);
        assertEquals(calcedPoint.y, finalPoint.y, 0);
    }
    */

}