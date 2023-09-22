import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Facture implements Serializable {
    private int ID;
    private String nom;
    private double montant;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Facture (){
        setID();
        setNom();
        setMontant();
    }

    public Facture (int ID, String nom, double montant) {
        this.ID = ID;
        this.nom = nom;
        this.montant = montant;
    }

    private void setID() {
        System.out.println("Veuillez entrez le no de ID:");
        while (true) {
            try {
                this.ID = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }

    private void setNom() {
        System.out.println("Veuillez entrez le nom de l'acheteur:");
        while (true) {
            try {
                this.nom = reader.readLine();
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }

    private void setMontant() {
        System.out.println("Veuillez entrez le montant de la facture:");
        while (true) {
            try {
                this.montant = Double.parseDouble(reader.readLine());
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }

    public String getID() {return String.valueOf(this.ID);}
    public String getNom() {return this.nom;}
    public String getMontant() {return String.valueOf(this.montant);}
}
