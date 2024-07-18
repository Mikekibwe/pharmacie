public class Prescription extends Medicament {
    private String prescriptionDetails;

    public Prescription(String id, String nom, int quantite, String prescriptionDetails) {
        super(id, nom, quantite);
        this.prescriptionDetails = prescriptionDetails;
    }

    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }

    @Override
    public String toString() {
        return super.toString() + ", Détails de la prescription: " + prescriptionDetails;
    }
}
