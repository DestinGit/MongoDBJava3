package alwaysMongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

public class AlwaysDataInsert {

    public static void main(String[] args) {
        try {
            // Connexion a mongoDB
//            MongoClientURI uri = new MongoClientURI("mongodb://m2icdi:mdp12345@185.31.40.41/m2icdi_cine");
            MongoClientURI uri = new MongoClientURI("mongodb://m2icdi:mdp12345@185.31.40.41/m2icdi_cine");
            
            MongoClient mongoClient = new MongoClient(uri);

            System.out.println(mongoClient);
            MongoDatabase db = mongoClient.getDatabase("m2icdi_cine");
            System.out.println(db);

            // La collection
            String lsCollectionName = "films";
            MongoCollection collection = db.getCollection(lsCollectionName);
            // Le document a ajouter
            Document film = new Document();
            film.append("titre", "21 grammes");
            film.append("annee", "2007");
            film.append("genre", "Comédie");

            List<String> listeActeurs = new ArrayList();
            listeActeurs.add("Sean Penn");
            listeActeurs.add("Charlotte Gainsbourg");
            listeActeurs.add("Naomi Watts");
            film.append("acteurs", listeActeurs);
            
            String[] tRealisateurs = new String[2];
            tRealisateurs[0] = "Inconnu";
            tRealisateurs[1] = "Chen";
            film.append("realisateurs", Arrays.asList(tRealisateurs));
            
            // Ajout (void)
            collection.insertOne(film);
            System.out.println("Nouveau film ajouté !");

            // La deconnexion de la BD n'existe pas
            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("UnknownHostException : " + e.getMessage());
        }
    } /// main
} /// class AlwaysDataInsert
