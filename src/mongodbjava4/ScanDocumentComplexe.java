/*
 ScanDocumentComplexe.java
 Afficher des documents de la même collection de schemas differents
 Existence de tableau

 Pour faire encore mieux il faudrait mettre en palce de la récursivité
 */
package mongodbjava4;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Set;
import connexionMongo.ConnexionMongoDB;
import org.bson.Document;

/**
 *
 * @author pascal
 */
public class ScanDocumentComplexe {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // CNX serveur et BD MDB
//            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//            MongoDatabase db = mongoClient.getDatabase("cours");
            ConnexionMongoDB cnx = new ConnexionMongoDB();
            MongoDatabase db = cnx.getDB("185.31.40.41", "27017", "m2icdi", "mdp12345", "m2icdi_cine");
            MongoCollection collection = db.getCollection("films");

            System.out.println("***** Hétérogènes *****");
            FindIterable<Document> curseur = collection.find();
            for (Document document : curseur) {
                System.out.println("***** Un document *****");
                Set<String> cles = document.keySet();
                for (String cle : cles) {
                    System.out.println(cle + " : " + document.get(cle));
                    Object valeur = document.get(cle);
                    System.out.println(valeur.getClass());
                    if (valeur instanceof java.util.ArrayList) {
                        System.out.println("Tableau");
                        ArrayList tableau = (ArrayList) valeur;
                        for (Object object : tableau) {
                            System.out.println(object);
                        } /// for éléments du tableau
                    } /// if tableau
                } /// for clés
            } /// for documents

            // DCNX serveur et BD MDB
            //mongoClient.close();
            
            cnx.seDeconnecter();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } /// main

} /// class
