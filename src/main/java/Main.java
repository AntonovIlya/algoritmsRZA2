public class Main {
    private static InputData inputData = new InputData();

    public static void main(String[] args) {
        System.out.println(Time.getCurrentHour());
        createCharts();
        inputData.readFile();
        System.out.println("Время работы программы: " + Time.getCurrentHour() + " мс");
    }
    private static void createCharts(){
        Charts.createAnalogChart("PhAvn", 0);
        Charts.addSeries("PhAvn, A", 0, 0);
        Charts.addSeries("PhAvnModule, A", 0, 1);
        Charts.addSeries("PhAvnArgument, A", 0, 2);

        Charts.createAnalogChart("PhBvn", 1);
        Charts.addSeries("PhBvn, A", 1, 0);
        Charts.addSeries("PhBvnModule, A", 1, 1);
        Charts.addSeries("PhBvnArgument, A", 1, 2);

        Charts.createAnalogChart("PhCvn", 2);
        Charts.addSeries("PhCvn, A", 2, 0);
        Charts.addSeries("PhCvnModule, A", 2, 1);

        Charts.createAnalogChart("PhAnn", 3);
        Charts.addSeries("PhAnn, A", 3, 0);
        Charts.addSeries("PhAnnModule, A", 3, 1);


        Charts.createAnalogChart("PhBnn", 4);
        Charts.addSeries("PhBnn, A", 4, 0);
        Charts.addSeries("PhBnnModule, A", 4, 1);


        Charts.createAnalogChart("PhCnn", 5);
        Charts.addSeries("PhCnn, A", 5, 0);
        Charts.addSeries("PhCnnModule, A", 5, 1);

        Charts.createAnalogChart("Signal", 6);
        Charts.addSeries("Signal", 6, 0);

    }
}
