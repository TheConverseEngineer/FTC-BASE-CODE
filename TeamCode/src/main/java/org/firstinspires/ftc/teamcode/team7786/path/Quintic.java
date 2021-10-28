package org.firstinspires.ftc.teamcode.team7786.path;

class Quintic {
  
  private static final RealMatrix coefficientMatrix = MatrixUtils.createRealMatrix(new double[][]{{0, 0, 0, 0, 0, 1},
                                                                                                  {0, 0, 0, 0, 1, 0},
                                                                                                  {0, 0, 0, 2, 0, 0},
                                                                                                  {1, 1, 1, 1, 1, 1},
                                                                                                  {5, 4, 3, 2, 1, 0},
                                                                                                   {20, 12, 6, 2, 0, 0}});
  private static final DecompositionSolver coeffSolver = new LUDecomposition(coefficientMatrix).getSolver();
  
  private double a, b, c, d, e, f;
  
  public Quintic (double S, double SD, double SSD, double E, double ED, double ESD) {
    RealMatrix allCoeffs = coeffSolver.solve(new MatrixUtils.createRealMatrix(new double[][]{{S, SD, SSD, E, ED, ESD}}));
    a = allCoeffs.getEntry(0, 0);
    
  }
}
