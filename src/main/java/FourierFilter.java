public class FourierFilter {
    private CurrentData cd;
    private CurrentF currentF = new CurrentF();
    private Logic logic = new Logic(currentF);
    public static int step = 20;
    private double[][] phABC = new double[6][step]; //arrays current selections (PhAvn,PhBvn,PhCvn,PhAnn,PhBnn,PhCnn)
    private short count = 0;

    public FourierFilter(CurrentData cd) {
        this.cd = cd;
    }

    public void processing() {
        for (int i = 0; i < 6; i++) {
            phABC[i][count] = cd.phABC[i];
        }
        fourier50(phABC);
        fourier100(phABC);
        logic.log();
        if (++count >= step) count = 0;

    }



    //Дискретное преобразование Фурье 1-ая гармоника
    private void fourier50(double[][] mas) {
        double[] Fx = new double[6];
        double[] Fy = new double[6];
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < step; i++) {
                Fx[j] += 0.1 * Math.sin(0.1 * i * Math.PI) * mas[j][i];
                Fy[j] += 0.1 * Math.cos(0.1 * i * Math.PI) * mas[j][i];
            }
            double F = Math.sqrt((Math.pow(Fx[j], 2) + Math.pow(Fy[j], 2)) * 0.5); // module
            Charts.addAnalogData(j, 1, F);
        }
        currentF.setF50algebraic(shiftPhase(Fx, Fy));
    }

    //Дискретное преобразование Фурье 2-ая гармоника
    private void fourier100(double[][] mas) {
        double[] Fx = new double[6];
        double[] Fy = new double[6];
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < step; i++) {
                Fx[j] += 0.1 * Math.sin(0.2 * i * Math.PI) * mas[j][i];
                Fy[j] += 0.1 * Math.cos(0.2 * i * Math.PI) * mas[j][i];
            }
        }
        currentF.setF100algebraic(shiftPhase(Fx, Fy));
    }

    // Соединение в треугольник вторичной обм. ТТ ВН
    private double[][] shiftPhase(double[] Fx, double[] Fy) {
        double[][] F = new double[2][6];
        for (int j = 0; j < 6; j++) {
            if (j == 0) { // Phase Avn
                F[0][j] = (Fx[j] - Fx[j + 2]) / Math.sqrt(3); // Вычитание координат по х
                F[1][j] = (Fy[j] - Fy[j + 2]) / Math.sqrt(3); // Вычитание координат по y
            } else if (j == 1 || j == 2) { // Phase Bvn and Phase Cvn
                F[0][j] = (Fx[j] - Fx[j - 1]) / Math.sqrt(3); // Вычитание координат по х
                F[1][j] = (Fy[j] - Fy[j - 1]) / Math.sqrt(3); // Вычитание координат по y
            } else {
                F[0][j] = Fx[j];
                F[1][j] = Fy[j];
            }
        }
        return F;
    }
}
