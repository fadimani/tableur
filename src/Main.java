import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, FormuleCycliqueException {



        Grille g = new Grille(20,20);

        Cell a1 = new Cell("A1", 15.0 );
        Cell c1 = new Cell("C1");
        Cell b3 = new Cell("B3", 25.0);

        Cell c5 = new Cell("C5", new Addition(a1,b3));

        Cell b4 = new Cell("B4", new Soustraction(a1,c5));

        Cell c2 = new Cell("C2", new Division(b4,c5));

        Cell e3 = new Cell("E3", new Addition(new Soustraction(a1,c2),b3));
        Cell b6 = new Cell("B6", new Multiplication(b3,c2));

        Cell c3 = new Cell("C3", new Addition(e3,b3));

        Cell d3 = new Cell("D3", new Addition(c1,c3));

        Cell d4 = new Cell("D4", new Somme(a1,a1,a1,a1,a1,a1));
        Cell d5 = new Cell("D5", new Moyenne(a1,a1,a1,a1,a1,a1));


        g.addAllCells(a1,c1,b3,c5,b4,c2,e3,b6,c3,d3,d4,d5);

        g.showAllCellsTopologicalOrder();

        System.out.println("---------------------------------------------------");

        try {
            g.editCell("A1", new Addition(b3, new Addition(a1, a1)));
        } catch (FormuleCycliqueException e) {
            System.out.println(e.getMessage());
        }


        try {
            g.editCell("C5", new Addition(b3, new Addition(b6, a1)));
        } catch (FormuleCycliqueException e) {
            System.out.println(e.getMessage());
        }

        g.showAllCellsTopologicalOrder();

        System.out.println("---------------------------------------------------");















//        File file = new File("C:/Users/imani/IdeaProjects/tableur/src/graph.bin");

//        g.exportGrille(file);
//
//        System.out.println("---------------------------------------------------");
//
//        Grille gRead = Grille.importGrille(file);
//
//        gRead.showAllCellsTopologicalOrder();






//        g.showAllCells();
//        System.out.println(g.getCell("B3"));
//        g.editCell("B3", 10);
//        System.out.println(g.getCell("B3"));
//        g.editCell("B4", 20);




//        g.editCell("B3",10);
////
//        System.out.println("-----------------");
//        g.showAllCells();





    }
}