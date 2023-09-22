import java.io.*;
import java.util.ArrayList;

public class DatabaseHandler {
    private static ArrayList<Voiture> voituresList = new ArrayList<Voiture>();
    private static ArrayList<Facture> facturesList = new ArrayList<Facture>();

    static void ReadFile() {
        ObjectInputStream input;
        File voituresFile = new File("voitures.dat");
        File facturesFile = new File("factures.dat");

        if(!voituresFile.exists() || !facturesFile.exists()) {
            Voiture v = new Voiture(0,"Hyundai","Accent","Gris",2010,1073,10000);
            voituresList.add(v);
            Facture f = new Facture(0,"Michael",10000);
            facturesList.add(f);
            CreateReadFile(voituresFile, facturesFile, voituresList, facturesList);
        }

        try {
            input = new ObjectInputStream(new FileInputStream(voituresFile));
            voituresList = SetVoituresList(input);
            input.close();
            input = new ObjectInputStream(new FileInputStream(facturesFile));
            facturesList = SetFacturesList(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void CreateReadFile(File voituresFile, File facturesFile, ArrayList<Voiture> v, ArrayList<Facture> f) {
        ObjectOutputStream output;
        try {
            if (!voituresFile.exists()) {
                if (voituresFile.createNewFile()) {
                    output = new ObjectOutputStream(new FileOutputStream(voituresFile));
                    output.writeObject(voituresList);
                    System.out.println("Le fichier a ete cree :  " + voituresFile.getName());
                }
                else { System.out.println("File" + voituresFile.getName() + " already exists."); }
            }

            if (!facturesFile.exists()) {
                if(facturesFile.createNewFile()){
                    output = new ObjectOutputStream(new FileOutputStream(facturesFile));
                    output.writeObject(facturesList);
                    System.out.println("Le fichier a ete cree : " + facturesFile.getName());
                    System.out.println(" ");
                }
                else { System.out.println("Fichier"+facturesFile.getName()+" existe deja.");}
            }

        } catch (IOException e) { System.out.println("Il y a eu une erreur"); e.printStackTrace(); }
    }

    private static ArrayList<Voiture> SetVoituresList(ObjectInputStream input) {
        try {
            return (ArrayList<Voiture>) input.readObject();
            } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Voiture>();
    }

        private static ArrayList<Facture> SetFacturesList(ObjectInputStream input) {
            try {
                return (ArrayList<Facture>) input.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<Facture>();
        }

    static String AddVoiture(Voiture voiture, File voituresFile) {
        try {
            voituresList.add(voiture);
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(voituresFile));
            output.writeObject(voituresList);
            output.close();
            return "La voiture a ete ajoutee.";
        } catch (Exception e) {e.printStackTrace();return "La voiture n'a pas ete ajoutee. Un probleme est survenu";}
    }

    static String AddFacture(Facture facture, File facturesFile) {
        try {
            facturesList.add(facture);
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(facturesFile));
            output.writeObject(facturesList);
            output.close();
            return "La facture a ete ajoutee.";
        } catch (Exception e) {e.printStackTrace(); return "La facture n'a pas ete ajoutee. Un probleme est survenu";}
    }

    static ArrayList<Voiture> VoitureParSerie(String substring) {
        ArrayList<Voiture> list = new ArrayList<Voiture>();
        for (int i = 0; i < voituresList.size(); i++) {
            if (voituresList.get(i).getSerie().equals(substring)) {
                list.add(voituresList.get(i));
            }
        }
        return list;
    }


    static ArrayList<String> GetSerie() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < voituresList.size(); i++) {
            String serie = voituresList.get(i).getSerie();
            if(!list.contains(serie)) {list.add(serie);}
        }
        return list;
    }


    static ArrayList<Voiture> VoitureParMarque(String substring) {
        ArrayList<Voiture> list = new ArrayList<Voiture>();
        for (int i = 0; i < voituresList.size(); i++) {
            if (voituresList.get(i).getMarque().equals(substring)) {
                list.add(voituresList.get(i));
            }
        }
        return list;
    }

    static ArrayList<String> GetMarque() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < voituresList.size(); i++) {
            String marque = voituresList.get(i).getMarque();
            if(!list.contains(marque)) {list.add(marque);}
        }
        return list;
    }

    static ArrayList<Facture> FactureParID(String substring) {
        ArrayList<Facture> list = new ArrayList<>();
        for (int i = 0; i < facturesList.size(); i++) {
            if (facturesList.get(i).getID().equals(substring)) {
                list.add(facturesList.get(i));
            }
        }
        return list;
    }

    static ArrayList<String> GetID() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < facturesList.size(); i++) {
            String ID = facturesList.get(i).getID();
            if(!list.contains(ID)) {list.add(ID);}
        }
        return list;
    }

    static ArrayList<Facture> FactureParNom(String substring) {
        ArrayList<Facture> list = new ArrayList<>();
        for (int i = 0; i < facturesList.size(); i++) {
            if (facturesList.get(i).getNom().equals(substring)) {
                list.add(facturesList.get(i));
            }
        }
        return list;
    }

    static ArrayList<String> GetNom() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < facturesList.size(); i++) {
            String nom = facturesList.get(i).getNom();
            if(!list.contains(nom)) {list.add(nom);}
        }
        return list;
    }


}
