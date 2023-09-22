import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Voiture implements Serializable {
    private int serie;
    private String marque;
    private String modele;
    private String couleur;
    private int annee;
    private double poids;
    private double prix;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Voiture() {
        setSerie();
        setMarque();
        setModele();
        setCouleur();
        setAnnee();
        setPoids();
        setPrix();
    }

    public Voiture(int serie, String marque, String modele, String couleur, int annee, double poids, double prix){
        this.serie = serie;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.annee = annee;
        this.poids = poids;
        this.prix = prix;
    }

    private void setSerie() {
        System.out.println(" ");
        System.out.println("Veuillez entrez le no de serie:");
        while (true) {
            try {
                this.serie = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }

    private void setMarque() {
        System.out.println("Veuillez entrez la marque:");
        while (true) {
            try {
                this.marque = reader.readLine();
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }
    private void setModele() {
        System.out.println("Veuillez entrez le modele:");
        while (true) {
            try {
                this.modele = reader.readLine();
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }
    private void setCouleur() {
        System.out.println("Veuillez entrez la couleur:");
        while (true) {
            try {
                this.couleur = reader.readLine();
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }
    private void setAnnee() {
        System.out.println("Veuillez entrez l'annee:");
        while (true) {
            try {
                this.annee = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }
    private void setPoids() {
        System.out.println("Veuillez entrez le poids:");
        while (true) {
            try {
                this.poids = Double.parseDouble(reader.readLine());
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }
    private void setPrix() {
        System.out.println("Veuillez entrez le prix:");
        while (true) {
            try {
                this.prix = Double.parseDouble(reader.readLine());
                System.out.println(" ");
                break;
            } catch (Exception e) {Client.Invalid();}
        }
    }


    public String getSerie() {return String.valueOf(this.serie);}
    public String getMarque() {return this.marque;}
    public String getModele() {return this.modele;}
    public String getCouleur() {return this.couleur;}
    public String getAnnee() {return String.valueOf(this.annee);}
    public String getPoids() {return String.valueOf(this.poids);}
    public String getPrix() {return String.valueOf(this.prix);}
}
