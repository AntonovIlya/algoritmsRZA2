public class Logic {
    private CurrentF cF;
    private OutputData od = new OutputData();
    private boolean flag1 = true;
    private int[] count = {0,0,0};

    public Logic(CurrentF cF) {
        this.cF = cF;
    }

    public void log() {
        if (flag1) {
            puskOrg();
        }
        od.process(flag1);
    }

    private boolean tormCharacteristic(double Idif, double Itorm) {
        double Idif0 = 0.300;
        double ktorm2 = 0.094;
        double Itorm2 = -1.183;
        double b = -ktorm2 * Itorm2;
        return ((Idif > Idif0) && (Idif > (ktorm2 * Itorm) + b));
    }

    private void puskOrg() {
        double IvtorVN = 3.608;
        for (int i = 0; i <= 2; i++) {
            if (cF.algebraicToModule50()[1][i] < 0.05) break;
            double Fx50 = cF.F50algebraic[0][i] + cF.F50algebraic[0][i + 3];
            double Fy50 = cF.F50algebraic[1][i] + cF.F50algebraic[1][i + 3];
            double Fx100 = cF.F100algebraic[0][i] + cF.F100algebraic[0][i + 3];
            double Fy100 = cF.F100algebraic[1][i] + cF.F100algebraic[1][i + 3];
            double Idif50 = Math.sqrt((Math.pow(Fx50, 2) + Math.pow(Fy50, 2)) * 0.5) / IvtorVN;
            double Idif100 = Math.sqrt((Math.pow(Fx100, 2) + Math.pow(Fy100, 2)) * 0.5) / IvtorVN;
            double Itorm50 = (cF.algebraicToModule50()[1][i] + cF.algebraicToModule50()[1][i]) / IvtorVN;
            if (tormCharacteristic(Idif50, Itorm50)) {
                if (++count[i]>= FourierFilter.step & (Idif100/Idif50 < 0.15)){
                    flag1 = false;
                    break;
                }
            }
        }
    }
}

