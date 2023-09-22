import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    static boolean exit = false;
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!exit){
            System.out.println
                    ("1. Nouvelle Voiture \n" +
                    "2. Nouvelle Facture \n" +
                    "3. Afficher voiture par no de serie \n" +
                    "4. Afficher voiture par marque \n" +
                    "5. Afficher facture par no de ID \n" +
                    "6. Afficher facture par nom de l'acheteur \n" +
                    "0. Quitter");
            try {
                MenuHandler.Handler( Integer.parseInt( reader.readLine() ) );
                Thread.sleep(2500);
            }
            catch (Exception e) { e.printStackTrace(); Client.Invalid();}
        }
    }
    public static void Invalid() {
        System.out.println(" ");
        System.out.println("Entree invalide! Veuillez reessayer.");
        System.out.println(" ");
    }
}

class MenuHandler extends Client{

    public static void Handler(int MenuChoice) throws IOException, ClassNotFoundException {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost", 5056);
            ObjectOutputStream Output = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream Input = new ObjectInputStream(clientSocket.getInputStream());
            String request;

            switch (MenuChoice) {
                case 1 -> {
                    Voiture voiture = new Voiture();
                    Output.writeObject(voiture);
                    System.out.println((String) Input.readObject());
                    System.out.println(" ");
                }
                case 2 -> {
                    Facture facture = new Facture();
                    Output.writeObject(facture);
                    System.out.println((String) Input.readObject());
                }
                case 3 -> {
                    request = "3";
                    Output.writeObject(request);
                    System.out.println("Les numeros de series disponibles sont : " + ArraytoList(Input.readObject()));
                    System.out.println("Veuillez entrez le no de serie :");
                    request = reader.readLine();
                    Output.writeObject(request);
                    AfficherVoitureArray((ArrayList<Voiture>) Input.readObject());
                }
                case 4 -> {
                    request = "4";
                    Output.writeObject(request);
                    System.out.println("Les marques disponibles sont : " + ArraytoList(Input.readObject()));
                    System.out.println("Veuillez entrez la marque :");
                    request = reader.readLine();
                    Output.writeObject(request);
                    AfficherVoitureArray((ArrayList<Voiture>) Input.readObject());
                }
                case 5 -> {
                    request = "5";
                    Output.writeObject(request);
                    System.out.println("Les ID disponibles sont : " + ArraytoList(Input.readObject()));
                    System.out.println("Veuillez entrez le no de ID :");
                    request = reader.readLine();
                    Output.writeObject(request);
                    AfficherFactureArray((ArrayList<Facture>) Input.readObject());
                }
                case 6 -> {
                    request = "6";
                    Output.writeObject(request);
                    System.out.println("Les noms disponibles sont : " + ArraytoList(Input.readObject()));
                    System.out.println("Veuillez entrez le nom de l'acheteur :");
                    request = reader.readLine();
                    Output.writeObject(request);
                    AfficherFactureArray((ArrayList<Facture>) Input.readObject());
                }
                case 0 -> {
                    Exit obj = new Exit();
                    Output.writeObject(obj);
                    Client.exit = true;
                }
                default -> Client.Invalid();
            }
            Output.close();
            Input.close();
            clientSocket.close();
        }
        catch (Exception e) {e.printStackTrace();}
    }

    private static void AfficherVoitureArray(ArrayList<Voiture> result) {
        if(result.isEmpty())
        {
            System.out.println(" ");
            System.out.println("Il n'y a pas de voitures correspondant a votre critere.");
            System.out.println(" ");
        }
        for (int i = 0; i < result.size(); i++) {
            if (i == 0) {System.out.println(" ");}
            System.out.println("Voiture " + (i+1));
            System.out.println("No de serie : " + result.get(i).getSerie());
            System.out.println("Marque : " + result.get(i).getMarque());
            System.out.println("Modele : " + result.get(i).getModele());
            System.out.println("Couleur : " + result.get(i).getCouleur());
            System.out.println("Annee : " + result.get(i).getAnnee());
            System.out.println("Poids : " + result.get(i).getPoids());
            System.out.println("Prix : " + result.get(i).getPrix());
            System.out.println(" ");
        }
    }

    private static void AfficherFactureArray(ArrayList<Facture> result) {
        for (int i = 0; i < result.size(); i++) {
            if (i == 0) {System.out.println(" ");}
            System.out.println("No de facture : " + result.get(i).getID());
            System.out.println("Nom : " + result.get(i).getNom());
            System.out.println("Montant : " + result.get(i).getMontant());
            System.out.println(" ");
        }
    }

    private static String ArraytoList(Object readObject) {
        if (!(readObject instanceof ArrayList)) {
            return "Probleme: le serveur n'a pas retourner de liste...";
        }
        ArrayList<String> array = (ArrayList<String>) readObject;
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            String serie = array.get(i);
            if(list.indexOf(serie) == -1) {
                if (i != array.size() - 1) {
                    list.append(serie).append(", ");
                } else {
                    list.append(serie);
                }
            }
        }
        String listString = list.toString();
        if(listString.endsWith(", ")){
            listString = listString.substring(0,listString.length()-2);
        }
        return listString;
    }
}

class Exit implements Serializable{
    final boolean e;
    Exit(){
        this.e = true;
    }
}

