import hotelmanagementsystem.Chambre;
import hotelmanagementsystem.Hotel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int choix;

        do {
            System.out.println("\n--- Bienvenu ---");
            System.out.println("1. Créer une réservation");
            System.out.println("2. Modifier une réservation");
            System.out.println("3. Annuler une réservation");
            System.out.println("4. Afficher les réservations");
            System.out.println("5. Vérifier la disponibilité des chambres");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Entrez le nom du client : ");
                    String client = scanner.nextLine();
                    System.out.print("Entrez le numéro de la chambre : ");
                    int chambreID = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.print("Entrez la date de check-in (yyyy-MM-dd) : ");
                    String checkInStr = scanner.nextLine();
                    LocalDate checkIn = LocalDate.parse(checkInStr, formatter);
                    System.out.print("Entrez la date de check-out (yyyy-MM-dd) : ");
                    String checkOutStr = scanner.nextLine();
                    LocalDate checkOut = LocalDate.parse(checkOutStr, formatter);

                    Chambre chambre = hotel.getChambreById(chambreID);

                    if (chambre != null) {
                        hotel.ajoutReservation(client, chambre, checkIn, checkOut);
                    } else {
                        System.out.println("La chambre avec l'ID " + chambreID + " n'existe pas.");
                    }
                    break;
                case 2:

                    hotel.modifierReservation(scanner);
                    break;
                case 3:

                    hotel.annulerReservation(scanner);
                    break;
                case 4:
                     hotel.getReservations();
                    break;
                case 5:
                    System.out.println("Chambres disponibles : ");
                    for (Chambre chambreDisponible : hotel.getChambresDisponibles()) {
                        System.out.println("Chambre " + chambreDisponible.getChambreID());
                    }
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }

        } while (choix != 0);

        scanner.close();
    }
}
