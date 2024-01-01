public abstract class OperationNargs extends Formule{

    @Override
    public String toString() {
        String res = "";

        String operation = this.getClass().toString();
        operation = operation.split(" ")[1];
        res = operation + "(";


        for (int i = 0; i < casesInOperation.size(); i++) {
            res += casesInOperation.get(i).getCoord() ;
            if (i != casesInOperation.size() - 1) {
                res += ",";
            }
        }
        res += ")";
        return res;
    }
}
