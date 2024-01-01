import java.io.Serializable;
import java.util.*;

public abstract class Formule implements Serializable {
    abstract double eval();
    List<Cell> casesInOperation = new ArrayList<>();
    Map<String, Double> casesInOperationCoord = new LinkedHashMap<>();

    List<Formule> formulesInOperation = new ArrayList<>();




}