import java.io.*;

public class Main {

    public static void writeObjectToFile(Grille obj, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
    }
    public static Grille readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        Grille result = null;
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        result = (Grille) ois.readObject();
        return result;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {



        Grille g = new Grille(20,20);

        Cell a1 = new Cell("A1", 15.0 );
        Cell c1 = new Cell("C1");
        Cell b3 = new Cell("B3", 25.0);

        Cell c5 = new Cell("C5", new Addition(a1,b3));

        Cell b4 = new Cell("B4", new Addition(a1,c5));
//
        Cell c2 = new Cell("C2", new Addition(b4,c5));

        Cell e3 = new Cell("E3", new Addition(b3,new Addition(a1,c2)));
        Cell b6 = new Cell("B6", new Addition(b3,c2));
//
        Cell c3 = new Cell("C3", new Addition(e3,b3));
//
        Cell d3 = new Cell("D3", new Addition(c1,c3));


        g.addAllCells(a1,c1,b3,c5,b4,c2);
//        g.addAllCells(a1,c1,b3,c3,d3);


        g.showAllCellsTopologicalOrder();

        System.out.println("---------------------------------------------------");

        g.editCell("B4", new Addition(a1,c1));
        g.showAllCellsTopologicalOrder();

        System.out.println(c2);
        System.out.println(b4.getValeur());





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