import java.io.Serializable;
import java.util.*;

public abstract class Formule implements Serializable {
    abstract double eval();
    List<Cell> casesInOperation = new ArrayList<>();

    List<Formule> formulesInOperation = new ArrayList<>();

    public String getOperation() {
        String operation = this.getClass().toString();
        operation = operation.split(" ")[1];

        String op = switch (operation) {
            case "Addition" -> "+";
            case "Soustraction" -> "-";
            case "Multiplication" -> "*";
            case "Division" -> "/";
            default -> "";
        };
        return op;
    }


}