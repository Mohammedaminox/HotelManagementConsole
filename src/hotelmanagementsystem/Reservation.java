package hotelmanagementsystem;

import java.time.LocalDate;

public class Reservation {
    private String Client;
    private Chambre chambre;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(String Client, Chambre chambre, LocalDate checkIn, LocalDate checkOut){
        this.Client = Client;
        this.chambre = chambre;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.chambre.setDisponible(false);
    }
}
