public class CurrentF {
    public double[][] F50algebraic = new double[2][6]; // Fx + iFy
    public double[][] F100algebraic = new double[2][6]; // Fx + iFy

    public double[][] algebraicToModule50() { // Fx + iFy => F,phi
        double[][] F = new double[2][6];
        for (int j = 0; j < 6; j++) {
            F[0][j] = Math.toDegrees(Math.atan2(F50algebraic[1][j],F50algebraic[0][j])); // argument
            F[1][j] = Math.sqrt((Math.pow(F50algebraic[0][j], 2) + Math.pow(F50algebraic[1][j], 2)) * 0.5); // module
        }
        return F;
    }

    public double[][] getF50algebraic() {
        return F50algebraic;
    }

    public void setF50algebraic(double[][] f50algebraic) {
        F50algebraic = f50algebraic;
    }

    public double[][] getF100algebraic() {
        return F100algebraic;
    }

    public void setF100algebraic(double[][] f100algebraic) {
        F100algebraic = f100algebraic;
    }
}
