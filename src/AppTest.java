public class AppTest {
    public static int convertStringLigne(String coord){    // "B15"-> 15
        int x = Integer.parseInt(coord.replaceAll("[^0-9]", ""));
        return x;
    }

    public static String convertStringColonne(String coord){    // "B15"-> "B"
        String col= coord.replaceAll("[^A-Za-z]", "");
        return col;
    }

    public static String convertCL(String colonne, int ligne){   // ("B",15) -> "B15"

        return colonne+ligne;
    }
}
