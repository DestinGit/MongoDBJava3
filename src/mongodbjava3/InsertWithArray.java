package mongodbjava3;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class InsertWithArray {

    public static void main(String[] args) {
        try {
            // Connexion a mongoDB
            //MongoClientURI uri = new MongoClientURI("mongodb://m2icdi:mdp12345@185.31.40.41/m2icdi_cine");
            MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1/");
            MongoClient mongoClient = new MongoClient(uri);

            System.out.println(mongoClient);
            MongoDatabase db = mongoClient.getDatabase("cours");
            System.out.println(db);

            // La collection
            String lsCollectionName = "personnes";
            MongoCollection collection = db.getCollection(lsCollectionName);
            // Le document a ajouter
            Document doc = new Document();
            doc.append("nom", "Penn");
            doc.append("prenom", "Sean");

            List<String> listeTel = new ArrayList();
            listeTel.add("0102030405");
            listeTel.add("0123456789");
            listeTel.add("0607080900");

            doc.append("telephones", listeTel);

            // Ajout (void)
            collection.insertOne(doc);
            System.out.println("Nouvelle personne ajoutée !");

            // La deconnexion de la BD n'existe pas
            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("UnknownHostException : " + e.getMessage());
        }
    } /// main
} /// class InsertWithArray
