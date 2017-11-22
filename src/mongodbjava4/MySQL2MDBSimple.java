/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava4;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import connexionMongo.ConnexionMongoDB;
import java.sql.*;
import org.bson.Document;

public class MySQL2MDBSimple {

    public static void main(String[] args) {

        try {
            // CNX serveur et BD MySQL
            Connection lcn = DriverManager.getConnection("jdbc:mysql://mysql-m2iformationcdidl.alwaysdata.net:3306/m2iformationcdidl_cine", "147389", "mdp123");

            // CNX serveur et BD MDB
//            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//            MongoDatabase db = mongoClient.getDatabase("cours");
            ConnexionMongoDB cnx = new ConnexionMongoDB();
            MongoDatabase db = cnx.getDB("185.31.40.41", "27017", "m2icdi", "mdp12345", "m2icdi_cine");
//            MongoCollection collection = db.getCollection("films");
            
            // Création d'une collection
            String lsCollection = "genres";
            /*
             Il faut tester l'existence de la collection
             */
            if (!collectionExiste(db, lsCollection)) {
                db.createCollection(lsCollection);
            }
            
            MongoCollection collection = db.getCollection(lsCollection);

            // Exécution de la requête SQL et création du curseur
            String lsSelect = "SELECT * FROM genre";
            Statement lstSQL = lcn.createStatement();
            ResultSet lrs = lstSQL.executeQuery(lsSelect);
            // Boucle dans le curseur
            while (lrs.next()) {
                // Création d'un document MDB
                Document doc = new Document();
                doc.append("codeGenre", lrs.getString(2));
                doc.append("libelleGenre", lrs.getString(3));
                // Ajout du document à la collection
                collection.insertOne(doc);
            }

            // DCNX serveur et BD MDB
            // mongoClient.close();
            cnx.seDeconnecter();
            // DCNX serveur et BD MySQL
            lcn.close();

            // Fermetures MySQL
            lrs.close();
            lstSQL.close();

            System.out.println("C'est fini !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    } /// main

    /**
     *
     * @param bd
     * @param psCollection
     * @return
     */
    public static boolean collectionExiste(MongoDatabase bd, String psCollection) {
        boolean lbExiste = false;
        MongoIterable<String> listeCollections = bd.listCollectionNames();
        for (String nomCollection : listeCollections) {
            if (nomCollection.equals(psCollection)) {
                lbExiste = true;
            }
        }
        return lbExiste;
    } /// collectionExiste

} /// class

