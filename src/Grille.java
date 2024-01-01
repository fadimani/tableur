import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Grille implements Serializable {
    public int maxLigne;
    public int maxColonne;

    Graph cellsGraph = new Graph();

    public Grille(int l, int c) {
        maxLigne = l;
        maxColonne = c;
    }

    public void addCell(Cell c){
        this.cellsGraph.addVertex(c);
        addCellEdges(c);
    }
    public void addCellEdges(Cell c){
        if (c.getFormule() == null) return;
        for (Cell cell : c.getFormule().casesInOperation) {
            this.cellsGraph.addEdge(cell, c);
        }
    }

    public void addAllCells(Cell...c){
        for (Cell cell : c) {
            addCell(cell);
        }
    }
    public void removeCell(Cell c){
        this.cellsGraph.removeVertex(c);
        //! loop through all cell and remove it from their formules as well
    }
    Cell getCell(String coord){
        return this.cellsGraph.getVertex(coord);
    }
    void showAllCells(){
        for (Cell c : this.cellsGraph.getAllVerticies()) {
            System.out.println(c);
        }
    }

    void showAllCellsOrdered(){
        List<Cell> sortedCells = this.cellsGraph.getAllVerticies().stream()
                                    .sorted(Cell::compareTo)
                                    .toList();

        for (Cell c : sortedCells ) {
            System.out.println(c);
        }
    }

    void upadteGrilleStartingFrom(String coord){
        Cell cell = getCell(coord);
        if (cell.getFormule() != null) cell.updateCell();

        for (Cell c : this.cellsGraph.getAdjVertices(cell)) {
            upadteGrilleStartingFrom(c.getCoord());
        }
    }


    void editCell(String coord,double newValue){
        Cell cell = getCell(coord);
        cell.makeCellNotNull();
        //! we can only edit the value of a cell if it's not a formula
        if (cell.getFormule() == null){
            cell.setValeur(newValue);
        }
        else System.out.println("Can't edit a cell that has a formula");
        //! we need to update the value of all the cells that depend on this cell
        //! this can be improved by updating the cells in a breadth first search manner
        upadteGrilleStartingFrom(coord);
    }
    void editCell(String coord,Formule formule){
        Cell cell = getCell(coord);
        removeCell(cell);

        cell.makeCellNotNull();
        cell.setFormule(formule);
        addCell(cell);

        upadteGrilleStartingFrom(coord);

    }
    void editCell(String coord){
        Cell cell = getCell(coord);
        //! we can only edit the value of a cell if it's not a formula
        if (cell.getFormule() == null){
            cell.makeCellNull();
            upadteGrilleStartingFrom(coord);
        }
        else System.out.println("Can't edit a cell that has a formula");

    }

    int getCellCount(){
        return this.cellsGraph.getAllVerticies().size();
    }

    void printLvl(List<Cell> lvl, int nb){
        System.out.println("Niveau " + nb + " : ");
        for (Cell cell : lvl) {
            System.out.println("\t\t"+cell );
//            System.out.println("\t\t"+cell.getCoord() + " : "+ cell.getFormule()+ " = " + cell.getValeur() );
        }
        System.out.println();
    }



    void showAllCellsTopologicalOrder(){
        Graph cellsTopo = new Graph(this.cellsGraph);
        List<Cell> level0 = new ArrayList<>(cellsTopo.map.keySet().stream().filter(c ->c.getFormule() == null).toList()) ;
        printLvl(level0, 0);

        List<Cell> level_N = new ArrayList<>();
        List<Cell> level_N_Plus_1= new ArrayList<>() ;
        List<Cell> visited = new ArrayList<>(level0);

        int n = 1;
        while (cellsTopo.getVertexCount() != visited.size()){
            level_N = level0;

            for (Cell cell : cellsTopo.map.keySet()) {
                if (cell.getFormule() != null){
                    List<Cell> fathers = cellsTopo.fathersOf(cell);
                    if (level_N.containsAll(fathers) && !visited.contains(cell)){
                        level_N_Plus_1.add(cell);
                        visited.add(cell);
                    }
                }
            }
            printLvl(level_N_Plus_1, n);
            n++;
            level0.addAll(level_N_Plus_1);
            level_N_Plus_1.clear();
        }



    }

    public void exportGrille(File file){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            System.out.println("Serialized data is saved in "+file.getAbsolutePath());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static Grille importGrille(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Grille grille = (Grille) ois.readObject();
            ois.close();
            return grille;
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return null;
        }
    }















}
