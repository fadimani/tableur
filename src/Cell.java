
import java.io.Serializable;

public class Cell implements Serializable, Comparable<Cell> {

    public String colonne;
    public int ligne;
    public double valeur;

    public Formule formule;

    public boolean isNullCell = false;



    public String getColonne() {
        return colonne;
    }
    public void setColonne(String colonne) {
        this.colonne = colonne;
    }
    public int getLigne() {
        return ligne;
    }
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public double getValeur() {
        return valeur;
    }
    public void setValeur(double x){
        this.valeur = x;

    }

    public void setFormule(Formule formule){
        this.formule = formule;
        if (formule == null) setValeur(0);
        else {
            for (Cell cell : formule.casesInOperation) {
                if(cell.isNullCell){
                    this.makeCellNull();
                    return;
                }
            }
            setValeur(formule.eval());
        }
    }

    public Formule getFormule(){
        return this.formule;
    }

    public String getCoord(){
        return this.colonne + this.ligne;
    }

    public Cell(String coord, double valeur) {
        this.colonne = AppTest.convertStringColonne(coord);
        this.ligne = AppTest.convertStringLigne(coord);
        setValeur(valeur);
    }

    public void makeCellNull(){
        setValeur(0);
        this.isNullCell = true;
    }
    public void makeCellNotNull(){
        this.isNullCell = false;
    }
    public Cell(String coord) {
        this.colonne = AppTest.convertStringColonne(coord);
        this.ligne = AppTest.convertStringLigne(coord);
        this.makeCellNull();
    }

    public Cell(String coord, Formule formule) {
        this.colonne = AppTest.convertStringColonne(coord);
        this.ligne = AppTest.convertStringLigne(coord);
        setFormule(formule);
    }

    public void updateCell(){
        setValeur(formule.eval());

        for (Cell cell : formule.casesInOperation) {
            if(cell.isNullCell){
                this.makeCellNull();
                System.out.println("made cell null "+this.getCoord());
                System.out.println("cell updated "+this.getCoord());
                return;
            }
        }
        this.makeCellNotNull();
        System.out.println("cell updated "+this.getCoord());
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Cell)) return false;
        Cell c = (Cell) obj;
        return this.getCoord().equals(c.getCoord()) ;
    }

    @Override
    public int hashCode() {
        return this.getCoord().hashCode() ;
    }

    @Override
    public String toString() {
        String value = (this.isNullCell)? "NULL" : String.valueOf(this.getValeur());
        if (this.getFormule() == null) return this.getCoord() +  " : " + value ;
        return this.getCoord() + " : " + this.getFormule()+ " = " + value;
    }

    @Override
    public int compareTo(Cell o) {
        String col = this.getColonne();
        int lig = this.getLigne();
        if (lig == o.getLigne()) return col.compareTo(o.getColonne());
        else return Integer.compare(lig, o.getLigne());
    }
}
