import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PharmacyManager {
    private Map<String, Medicament> medicaments;

    public PharmacyManager() {
        medicaments = new HashMap<>();
    }

    public void ajouterMedicament(Medicament medicament) {
        medicaments.put(medicament.getId(), medicament);
        System.out.println("le medicament a été ajouter avec succès !");
    }

    public void supprimerMedicament(String id) {
        medicaments.remove(id);
        System.out.println("le medicament a été supprimer avec succès !");
    }

    public Medicament rechercherMedicamentParNom(String nom) {
        for (Medicament medicament : medicaments.values()) {
            if (medicament.getNom().equalsIgnoreCase(nom)) {
                return medicament;
            }
        }
        return null;
    }

    public Medicament rechercherMedicamentParId(String id) {
        return medicaments.get(id);
    }

    public void afficherMedicamentsParType(Class<?> type) {
        for (Medicament medicament : medicaments.values()) {
            if (type.isInstance(medicament)) {
                System.out.println(medicament);
            }
        }
    }

    public void modifierMedicament(String id, int nouvelleQuantite) {
        Medicament medicament = medicaments.get(id);
        if (medicament != null) {
            medicament.setQuantite(nouvelleQuantite);
            System.out.println("le medicament a été modier avec succès !");
        }
    }

    public void afficherMenu() {
        System.out.println("Menu:");
        System.out.println("1. Ajouter un médicament");
        System.out.println("2. Supprimer un médicament");
        System.out.println("3. Rechercher un médicament par nom ou identifiant");
        System.out.println("4. Afficher la liste des médicaments par type");
        System.out.println("5. Modifier un médicament par son identifiant");
        System.out.println("6. Lister les médicaments par lettre alphabétique");
        System.out.println("7. Afficher le nombre de médicaments en stock");
        System.out.println("8. Afficher les médicaments en rupture de stock");
        System.out.println("9. Afficher les médicaments avec une quantité faible");
        System.out.println("10. Afficher les médicaments par ordre alphabétique");
        System.out.println("11. Quitter");
    }

    public void listerMedicamentsParLettre(char lettre) {
        for (Medicament medicament : medicaments.values()) {
            if (medicament.getNom().charAt(0) == lettre) {
                System.out.println(medicament);
            }
        }
    }

    public void afficherNombreDeMedicamentsEnStock() {
        System.out.println("Nombre total de médicaments en stock: " + medicaments.size());
    }

    public void afficherMedicamentsEnRuptureDeStock() {
        for (Medicament medicament : medicaments.values()) {
            if (medicament.getQuantite() == 0) {
                System.out.println(medicament);
            }
        }
    }

    public void afficherMedicamentsAvecQuantiteFaible(int seuil) {
        for (Medicament medicament : medicaments.values()) {
            if (medicament.getQuantite() < seuil) {
                System.out.println(medicament);
            }
        }
    }

    public void afficherMedicamentsParOrdreAlphabetique() {
        medicaments.values().stream()
                .sorted((m1, m2) -> m1.getNom().compareToIgnoreCase(m2.getNom()))
                .forEach(System.out::println);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    System.out.println("Ajouter un médicament:");
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Quantité: ");
                    int quantite = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.print("Type (1: Vente Libre, 2: Prescription): ");
                    int type = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne

                    if (type == 1) {
                        ajouterMedicament(new VenteLibre(id, nom, quantite));
                    } else if (type == 2) {
                        System.out.print("Détails de la prescription: ");
                        String details = scanner.nextLine();
                        ajouterMedicament(new Prescription(id, nom, quantite, details));
                    }
                    break;
                case 2:
                    System.out.print("ID du médicament à supprimer: ");
                    id = scanner.nextLine();
                    supprimerMedicament(id);
                    break;
                case 3:
                    System.out.print("Nom ou ID du médicament à rechercher: ");
                    String recherche = scanner.nextLine();
                    Medicament medicament = rechercherMedicamentParNom(recherche);
                    if (medicament == null) {
                        medicament = rechercherMedicamentParId(recherche);
                    }
                    System.out.println(medicament != null ? medicament : "Médicament non trouvé.");
                    break;
                case 4:
                    System.out.print("Type (1: Vente Libre, 2: Prescription): ");
                    type = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    if (type == 1) {
                        afficherMedicamentsParType(VenteLibre.class);
                    } else if (type == 2) {
                        afficherMedicamentsParType(Prescription.class);
                    }
                    break;
                case 5:
                    System.out.print("ID du médicament à modifier: ");
                    id = scanner.nextLine();
                    System.out.print("Nouvelle quantité: ");
                    quantite = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.println("quantité modifié avec succè !");
                    modifierMedicament(id, quantite);
                    break;
                case 6:
                    System.out.print("Lettre alphabétique: ");
                    char lettre = scanner.nextLine().charAt(0);
                    listerMedicamentsParLettre(lettre);
                    break;
                case 7:
                    afficherNombreDeMedicamentsEnStock();
                    break;
                case 8:
                    afficherMedicamentsEnRuptureDeStock();
                    break;
                case 9:
                    System.out.print("Seuil de quantité faible: ");
                    int seuil = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    afficherMedicamentsAvecQuantiteFaible(seuil);
                    break;
                case 10:
                    afficherMedicamentsParOrdreAlphabetique();
                    break;
                case 11:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }
}
