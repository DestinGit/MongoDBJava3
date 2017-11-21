/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava3;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

/**
 *
 * @author formation
 */
public class BDsListe {
    public static void main(String[] args) {
        
        String lsDBName = "cours";
        boolean lbDBExist = false;
        String lsCollectionName = "produits";
        boolean lbCollectionExist = false;

        try {
            // Connexion a mongoDB
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.26.10.144:27017"));

            // Une BD
            MongoDatabase db = mongoClient.getDatabase(lsDBName);

                /*
                 Les BD
                 */
            MongoIterable<String> listeDB = mongoClient.listDatabaseNames();

//            MongoCursor<String> it = listeDB.iterator();
//            while (it.hasNext()) {
//                String lsDB = it.next();
//                System.out.println(lsDB);
//            }
// ALTERNATIVE ...
            for (String lsDB : listeDB) {
                System.out.println(lsDB);
//                if (lsDB.equals(lsDBName)) {
//                    lbDBExist = true;
//                }
            }

//            if (lbDBExist) {
//                MongoCollection collection = db.getCollection(lsCollectionName);
//                //System.out.println(collection);
//
//                /*
//                 Les collections
//                 */
//                MongoIterable<String> collectionListe = db.listCollectionNames();
//                for (String lsCollection : collectionListe) {
//                    if (lsCollection.equals(lsCollectionName)) {
//                        lbCollectionExist = true;
//                    }
//                }
//
//                /*
//                 Balayage du curseur
//                 */
//                if (lbCollectionExist) {
//                    FindIterable<Document> curseur = collection.find();
//                    for (Document document : curseur) {
//                        System.out.println(document.get("designation"));
//                    }
//                } else {
//                    System.out.println("La collection " + lsCollectionName + " n'existe pas");
//                }
//            } else {
//                System.out.println("La BD " + lsDBName + " n'existe pas !");
//            }

            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

    }
    
}
