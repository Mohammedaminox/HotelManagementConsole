package hotelmanagementsystem;

import java.time.LocalDate;

public class Reservation {

    //fields
    private String client;
    private Chambre chambre;
    private LocalDate checkIn;
    private LocalDate checkOut;


    //constructeur
    public Reservation(String client, Chambre chambre, LocalDate checkIn, LocalDate checkOut){
        this.client = client;
        this.chambre = chambre;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.chambre.setDisponible(false);
    }

    //Getters
    public String getClient() {
        return client;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    //Setters
    public void setClient(String client){
        this.client = client;
    }

    public void setCheckIn(LocalDate checkIn){
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut){
        this.checkOut = checkOut;
    }

    public void setChambre(Chambre Modifchambre){
        if(Modifchambre.isDisponible()){
            this.chambre.setDisponible(true);
            this.chambre = Modifchambre;
            this.chambre.setDisponible(false);
        } else {
            System.out.println("Cette chambre deja reserver");
        }
    }

    public void annuler(){
        this.chambre.setDisponible(true);
    }

    // Override the toString() method
    @Override
    public String toString() {
        return "Réservation :" +
                "client='" + client + '\'' +
                ", chambre=" + chambre.getChambreID() +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '.';
    }
}
