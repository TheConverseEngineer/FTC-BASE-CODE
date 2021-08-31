package org.firstinspires.ftc.teamcode.team7786.geometry;

import static org.junit.Assert.*;


import org.junit.Test;

public class PointTest {


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



}