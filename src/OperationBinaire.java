public abstract class OperationBinaire extends Formule {


    @Override
    public String toString() {
        String res = "";
        String operation = this.getClass().toString();
        operation = operation.split(" ")[1];

        String op = switch (operation) {
            case "Addition" -> "+";
            case "Soustraction" -> "-";
            case "Multiplication" -> "*";
            case "Division" -> "/";
            default -> "";
        };

        if (formulesInOperation.size()==0){
            String case1 = casesInOperation.get(0).colonne + casesInOperation.get(0).ligne;
            String case2 = casesInOperation.get(1).colonne + casesInOperation.get(1).ligne;
            res = "(" + case1 +" "+ op +" "+ case2 + ")" ;
        }
        else if (formulesInOperation.size()==1){
            String case1 = casesInOperation.get(0).colonne + casesInOperation.get(0).ligne;
            String formule1 = formulesInOperation.get(0).toString();
            res = "(" + case1 +" "+ op +" "+ formule1 + ")" ;
        }
        else if (formulesInOperation.size()==2){
            String formule1 = formulesInOperation.get(0).toString();
            String formule2 = formulesInOperation.get(1).toString();
            res = "(" +  formule1 +" "+ op +" "+ formule2 + ")" ;
        }

        return res;
    }
}
