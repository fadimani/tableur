import java.util.Arrays;

public class Moyenne extends OperationNargs {
    public Moyenne(Cell... cells) {
        casesInOperation.addAll(Arrays.asList(cells));
    }



    @Override
    double eval() {
        double res = 0;
        for (Cell cell : casesInOperation) {
            res += cell.getValeur();
        }
        res = res/casesInOperation.size();
        return res;
    }
}
