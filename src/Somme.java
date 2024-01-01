import java.util.Arrays;

public class Somme extends OperationNargs {


    public Somme(Cell... cells) {
        casesInOperation.addAll(Arrays.asList(cells));
    }



    @Override
    double eval() {
        double res = 0;
        for (Cell cell : casesInOperation) {
            res += cell.getValeur();
        }
        return res;
    }


}
