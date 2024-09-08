package hotelmanagementsystem;

public class Chambre {
    private int chambreID;
    private boolean disponible;


    public Chambre(int chambreID) {
        this.chambreID = chambreID;
        this.disponible = true;
    }

    public int getChambreID() {
        return chambreID;
    }


    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }


}
