public class Multiplication extends OperationBinaire{
    public Multiplication(Cell cell1, Cell cell2) {
        casesInOperation.add(cell1);
        casesInOperation.add(cell2);
    }

    public Multiplication(Cell cell, Formule formule) {
        casesInOperation.add(cell);
        formulesInOperation.add(formule);
        casesInOperation.addAll(formule.casesInOperation);

    }

    public Multiplication(Formule formule, Cell cell) {
        casesInOperation.add(cell);
        formulesInOperation.add(formule);
        casesInOperation.addAll(formule.casesInOperation);

    }



    public Multiplication(Formule formule1, Formule formule2) {
        formulesInOperation.add(formule1);
        formulesInOperation.add(formule2);

        casesInOperation.addAll(formule1.casesInOperation);
        casesInOperation.addAll(formule2.casesInOperation);

    }


    @Override
    double eval() {

        if (formulesInOperation.size()==0){
            double res = casesInOperation.get(0).getValeur() * casesInOperation.get(1).getValeur();
            return res;
        }
        else if (formulesInOperation.size()==1){
            double res = casesInOperation.get(0).getValeur() * formulesInOperation.get(0).eval();
            return res;
        }
        else if (formulesInOperation.size()==2){
            double res = formulesInOperation.get(0).eval() * formulesInOperation.get(1).eval();
            return res;
        }


        return -1;

    }
}
