package hotelmanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Hotel {

    private List<Chambre> chambres;
    private List<Reservation> reservations;


    public Hotel(int nombreDeChambre){
        chambres = new ArrayList<>();
        reservations = new ArrayList<>();
        for (int i = 1; i <= nombreDeChambre; i++){
            chambres.add(new Chambre(i));
        }
    }




    //Afficher les reservations
    public void afficherReservations() {
        if (!reservations.isEmpty()) {
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println("N°: " + (i + 1) + " - " + reservations.get(i));
            }
        } else {
            System.out.println("Aucune réservation trouvée");
        }
    }


    //Ajouter les reservations
    public void ajoutReservation() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.print("Entrez le nom du client : ");
        String client = scanner.nextLine();

        System.out.println("Voici toutes les chambres : ");
        for (Chambre chambre : chambres) {
            System.out.println("Chambre Numero : " + chambre.getChambreID());
        }
        System.out.print("Entrez le numéro de la chambre que vous voulez reserver : ");
        int chambreID = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        System.out.print("Entrez la date de check-in (yyyy-MM-dd) : ");
        String checkInStr = scanner.nextLine();
        LocalDate checkIn = LocalDate.parse(checkInStr, formatter);

        System.out.print("Entrez la date de check-out (yyyy-MM-dd) : ");
        String checkOutStr = scanner.nextLine();
        LocalDate checkOut = LocalDate.parse(checkOutStr, formatter);

        Chambre chambre = getChambreById(chambreID);

        if (chambre != null) {
            boolean isAvailable = true;
            for (Reservation reservation : reservations) {
                if (reservation.getChambre().getChambreID() == chambreID && reservation.overlaps(checkIn, checkOut)) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                Reservation newReservation = new Reservation(client, chambre, checkIn, checkOut);
                reservations.add(newReservation);
                System.out.println("Réservation effectuée avec succès pour le client " + client);
            } else {
                System.out.println("La chambre " + chambre.getChambreID() + " est déjà réservée pour ces dates.");
            }
        } else {
            System.out.println("La chambre avec l'ID " + chambreID + " n'existe pas.");
        }
    }


    //remener chamberes par id
    public Chambre getChambreById(int id) {
        for (Chambre chambre : chambres) {
            if (chambre.getChambreID() == id) {
                return chambre;
            }
        }
        return null;
    }


    //Annuler les réservations
    public void annulerReservation() {
        Scanner scanner = new Scanner(System.in);
        afficherReservations();
        if(reservations.isEmpty()){
            System.out.println("il ya aucune resevation");
            return;
        }
        System.out.println("Enter le numero de reservation que vous voulez annuler : ");
        int annuleResvationId = scanner.nextInt();
        scanner.nextLine();
            if (annuleResvationId <= 0 || annuleResvationId > reservations.size()){
                System.out.println("reservation introuvable");
                return;
            }
        Reservation reservationAnnuler = reservations.get(annuleResvationId - 1);

            reservationAnnuler.annuler();
            reservations.remove(annuleResvationId - 1);
        System.out.println("Reservation annuler successfully");

    }


    //Modifier les reservation
    public void modifierReservation() {
        Scanner scanner = new Scanner(System.in);
        afficherReservations();

        if (reservations.isEmpty()) {
            System.out.println("Il n'y a aucune réservation.");
            return;
        }

        System.out.print("Entrez l'ID de la réservation que vous voulez modifier : ");
        int editReservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (editReservationId <= 0 || editReservationId > reservations.size()) {
            System.out.println("Réservation introuvable.");
            return;
        }

        Reservation reservation = reservations.get(editReservationId - 1);
        System.out.println("Réservation à modifier : " + reservation);

        System.out.println("Entrez le nouveau nom du client (Appuyez sur Entrée pour skiper) : " + reservation.getClient());
        String newClient = scanner.nextLine();

        System.out.println("Entrez la nouvelle date de check-in (yyyy-MM-dd) (Appuyez sur Entrée pour skiper) : " + reservation.getCheckIn());
        String newCheckInStr = scanner.nextLine();

        System.out.println("Entrez la nouvelle date de check-out (yyyy-MM-dd) (Appuyez sur Entrée pour skiper) : " + reservation.getCheckOut());
        String newCheckOutStr = scanner.nextLine();

        LocalDate newCheckIn = newCheckInStr.isEmpty() ? reservation.getCheckIn() : LocalDate.parse(newCheckInStr);
        LocalDate newCheckOut = newCheckOutStr.isEmpty() ? reservation.getCheckOut() : LocalDate.parse(newCheckOutStr);

        // overlapping reservations
        boolean isAvailable = true;
        for (Reservation res : reservations) {
            if (res != reservation && res.getChambre().getChambreID() == reservation.getChambre().getChambreID() && res.overlaps(newCheckIn, newCheckOut)) {
                isAvailable = false;
                break;
            }
        }

        if (isAvailable) {
            if (!newClient.isEmpty()) {
                reservation.setClient(newClient);
            }
            reservation.setCheckIn(newCheckIn);
            reservation.setCheckOut(newCheckOut);

            System.out.println("Réservation modifiée avec succès.");
        } else {
            System.out.println("Les nouvelles dates se chevauchent avec une réservation existante pour cette chambre.");
        }
    }

}
