public class CurrentData {
    public double[] phABC = new double[6]; //array current instant values (PhABvn, PhBCvn, PhCAvn, PhAnn, PhBnn, PhCnn)

    public double[] getPhABC() {
        return phABC;
    }

    public void setPhABC(double[] phABC) {
        this.phABC = phABC;
    }

}