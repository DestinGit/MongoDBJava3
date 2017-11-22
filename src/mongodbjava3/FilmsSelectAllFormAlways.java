/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava3;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import java.util.Map.Entry;
import java.util.Set;
import org.bson.Document;
/**
 *
 * @author formation
 */
public class FilmsSelectAllFormAlways {

    public static void main(String[] args) {

//        String lsDBName = "cinescope";
        String lsDBName = "m2icdi_cine";
        String lsCollectionName = "films";

        try {
            // Connexion a mongoDB
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://m2icdi:mdp12345@mongodb-m2icdi.alwaysdata.net:27017"));
//            MongoClient mongoClient = new MongoClient("172.26.10.144", 27017);

            // La BD
            MongoDatabase db = mongoClient.getDatabase(lsDBName);

            // La collection
            MongoCollection collection = db.getCollection(lsCollectionName);
            System.out.println(collection);
            /*
             Balayage du curseur
             */
            FindIterable<Document> find = collection.find();
            MongoCursor<Document> curseur = find.iterator();
            while (curseur.hasNext()) {
                Document document = curseur.next();
                System.out.println(document.toJson());
//                Set<Entry<String, Object>> champs = document.entrySet();
//                for (Entry<String, Object> entry : champs) {
//                    System.out.print(entry.getKey());
//                    System.out.print(":");
//                    System.out.print(entry.getValue());
//                    System.out.print(" - ");
//                }
                System.out.println("");
            }
            // Fermeture du curseur
            curseur.close();

            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    } /// main
    
}
