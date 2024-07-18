public abstract class Medicament {
    private String id;
    private String nom;
    private int quantite;

    public Medicament(String id, String nom, int quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Quantité: " + quantite;
    }
}
