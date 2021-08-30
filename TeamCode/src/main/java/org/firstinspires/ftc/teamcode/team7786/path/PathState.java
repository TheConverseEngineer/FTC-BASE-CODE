package org.firstinspires.ftc.teamcode.team7786.path;

import org.firstinspires.ftc.teamcode.team7786.geometry.Point;

public class PathState
{

  public Point point;
  public int stage;
  public boolean complete;


  public PathState(Point p, int _stage, boolean _complete) {
    point = p;
    stage = _stage;
    complete = _complete;
  }


}
