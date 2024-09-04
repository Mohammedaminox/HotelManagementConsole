package hotelmanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {

    private List<Chambre> chambres;
    private List<Reservation> reservations;


    public Hotel(int nombreDeChambre){
        chambres = new ArrayList<>();
        reservations = new ArrayList<>();
        for (int i = 0; i <= nombreDeChambre; i++){
            chambres.add(new Chambre(i));
        }
    }

    //Afficher les chambres disponibles
    public List<Chambre> getChambresDisponibles(){
        List<Chambre> disponibles = new ArrayList<>();
        for (Chambre chambre : chambres){
            if (chambre.isDisponible()){
                disponibles.add(chambre);
            }
        }
        return disponibles;
    }

    //Afficher les reservations
    public void getReservations() {
        if (reservations.size() > 0) {
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println("ID: " + (i + 1) + " - " + reservations.get(i));
            }
        } else {
            System.out.println("Aucune réservation trouvée");
        }
    }


    //Ajouter les reservations
    public void ajoutReservation(String client, Chambre chambre, LocalDate checkIn, LocalDate checkOut){
            if(chambre.isDisponible()){
                Reservation newReservation = new Reservation(client, chambre, checkIn, checkOut);
                reservations.add(newReservation);
                chambre.setDisponible(false);
                System.out.println("Réservation effectuée avec succès pour le client " + client);
            } else {
                System.out.println("La chambre " + chambre.getChambreID() + " n'est pas disponible.");
            }
    }

    public Chambre getChambreById(int id) {
        for (Chambre chambre : chambres) {
            if (chambre.getChambreID() == id) {
                return chambre;
            }
        }
        return null;
    }

    //Annuler les réservations
    public void annulerReservation(Scanner scanner) {
        getReservations();
        if(reservations.isEmpty()){
            System.out.println("il ya aucune resevation");
            return;
        }
        System.out.println("Enter id de reservation que vous voulez modifier: ");
        int annuleResvationId = scanner.nextInt();
        scanner.nextLine();
            if (annuleResvationId <= 0 || annuleResvationId > reservations.size()){
                System.out.println("reservation introuvable");
                return;
            }
        Reservation reservationAnnuler = reservations.get(annuleResvationId - 1);

            reservationAnnuler.annuler();
            reservations.remove(annuleResvationId - 1);
        System.out.println("Reservation Modifier successfully");

//            if (reservation.getClient().equals(client)) {
//                reservation.annuler();
//                reservations.remove(reservation);
//                System.out.println("Réservation annulée avec succès pour le client " + client);
//                break;
//            }


    }

    //Modifier les reservation
    public void modifierReservation(Scanner scanner){
        getReservations();
        if(reservations.isEmpty()){
            System.out.println("il ya aucune resevation");
            return;
        }
        System.out.println("Enter id de reservation que vous voulez modifier: ");
        int editResvationId = scanner.nextInt();
        scanner.nextLine();

        if (editResvationId <= 0 || editResvationId > reservations.size()){
            System.out.println("reservation introuvable");
            return;
        }

        Reservation reservation = reservations.get(editResvationId - 1);
        System.out.println("Reservation a modifier : " + reservation);

        System.out.println("Enter le nouveau clientName  (press Enter to skip)" + reservation.getClient());
        String newClient = scanner.nextLine();


        if (!newClient.isEmpty()){
            reservation.setClient(newClient);
        }

        System.out.println("Enter le nouveau checkin date  (press Enter to skip) " + reservation.getCheckIn());
        String newCheckIn = scanner.nextLine();

        if (!newCheckIn.isEmpty()){
            reservation.setCheckIn(LocalDate.parse(newCheckIn));
        }

        System.out.println("Enter le nouveau checkout date  (press Enter to skip) " + reservation.getCheckOut());
        String newCheckOut = scanner.nextLine();

        if (!newCheckOut.isEmpty()){
            reservation.setCheckOut(LocalDate.parse(newCheckOut));
        }

        System.out.println("Reservation Modifier successfully");
    }
}
