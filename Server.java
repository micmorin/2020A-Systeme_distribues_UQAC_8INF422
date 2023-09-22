import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static boolean exit = false;

    public static void main(String[] args) throws IOException {
        DatabaseHandler.ReadFile();
        ServerSocket SServer = new ServerSocket(5056);

        while (!exit)
        {
            Socket SClient = null;

            try
            {
                System.out.println("Le Serveur est pret.");
                SClient = SServer.accept();
                System.out.println("Un nouveau client c'est connecte: " + SClient);
                ObjectInputStream ClientInput = new ObjectInputStream(SClient.getInputStream());
                ObjectOutputStream ClientOutput = new ObjectOutputStream(SClient.getOutputStream());

                System.out.println("Assignation d'un nouveau thread pour le client");
                System.out.println(" ");
                Thread t = new ClientHandler(SClient, ClientInput, ClientOutput);
                t.run();

            }
            catch (Exception e){
                if (SClient != null) {
                    SClient.close();
                }
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler extends Thread {
    final ObjectInputStream ClientInput;
    final ObjectOutputStream ClientOutput;
    final Socket ClientSocket;

    ClientHandler(Socket s, ObjectInputStream in, ObjectOutputStream out) {
        this.ClientSocket = s;
        this.ClientInput = in;
        this.ClientOutput = out;
    }

    @Override
    public void run() {
        Object received;
        Voiture voiture;
        Facture facture;
        File voituresFile = new File("voitures.dat");
        File facturesFile = new File("factures.dat");

        try {
            received = ClientInput.readObject();
            if (received instanceof Voiture) {
                voiture = (Voiture) received;
                String result = DatabaseHandler.AddVoiture(voiture, voituresFile);
                ClientOutput.writeObject(result);
            }
            else if (received instanceof Facture) {
                facture = (Facture) received;
                String result = DatabaseHandler.AddFacture(facture, facturesFile);
                ClientOutput.writeObject(result);
            }
            else if(received instanceof String){
                String request = (String) received;
                ArrayList result;
                switch (Integer.parseInt(String.valueOf(request.charAt(0)))){
                    case 3:
                        ClientOutput.writeObject(DatabaseHandler.GetSerie());
                        request = (String) ClientInput.readObject();
                        result = DatabaseHandler.VoitureParSerie(request.substring(0));
                        ClientOutput.writeObject((ArrayList<Voiture>) result);
                        break;

                    case 4:
                        ClientOutput.writeObject(DatabaseHandler.GetMarque());
                        request = (String) ClientInput.readObject();
                        result = DatabaseHandler.VoitureParMarque(request.substring(0));
                        ClientOutput.writeObject((ArrayList<Voiture>) result);
                        break;

                    case 5:
                        ClientOutput.writeObject(DatabaseHandler.GetID());
                        request = (String) ClientInput.readObject();
                        result = DatabaseHandler.FactureParID(request.substring(0));
                        ClientOutput.writeObject((ArrayList<Facture>) result);
                        break;

                    case 6:
                        ClientOutput.writeObject(DatabaseHandler.GetNom());
                        request = (String) ClientInput.readObject();
                        result = DatabaseHandler.FactureParNom(request.substring(0));
                        ClientOutput.writeObject((ArrayList<Facture>) result);
                        break;

                    default:
                }

            }
            else if (received instanceof Exit){
                Server.exit = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



