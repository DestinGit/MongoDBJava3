package alwaysMongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.Map.Entry;
import java.util.Set;
import org.bson.Document;

public class AlwaysDataSelectAll {

    public static void main(String[] args) {

        try {
            // Connexion au serveur
            MongoClientURI uri = new MongoClientURI("mongodb://m2icdi:mdp12345@185.31.40.41/m2icdi_cine");
            MongoClient mongoClient = new MongoClient(uri);

            System.out.println(mongoClient);
            MongoDatabase db = mongoClient.getDatabase("m2icdi_cine");
            System.out.println(db);

            // La collection
            String lsCollectionName = "films";
            MongoCollection collection = db.getCollection(lsCollectionName);
            // Les documents
            /*
             Balayage du curseur
             */
            FindIterable<Document> find = collection.find();
            MongoCursor<Document> curseur = find.iterator();
            while (curseur.hasNext()) {
                Document document = curseur.next();
                System.out.println("toJson");
                System.out.println(document.toJson());
                System.out.println("");
                System.out.println("Via Set Entry");
                Set<Entry<String, Object>> champs = document.entrySet();
                for (Entry<String, Object> entry : champs) {
                    System.out.print(entry.getKey());
                    System.out.print(":");
                    System.out.print(entry.getValue());
                    System.out.print(" - ");
                }
                System.out.println("");
            }
            // Fermeture du curseur
            curseur.close();

            // La deconnexion de la BD n'existe pas
            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("UnknownHostException : " + e.getMessage());
        }
    } /// main
} /// class AlwaysDataSelectAll
