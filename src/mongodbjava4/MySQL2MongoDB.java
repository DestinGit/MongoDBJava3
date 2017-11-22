/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava4;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connexionMongo.ConnexionMongoDB;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static mongodbjava4.MySQL2MDBSimple.collectionExiste;
import org.bson.Document;

/**
 *
 * @author formation
 */
public class MySQL2MongoDB {

    public static void main(String[] args) {
        mySQLTable2MongoDBCollection("appreciation");
    }

    public static void mySQLTable2MongoDBCollection(String psTable) {
//        List<String> lstTables = new ArrayList();

        try {
            // Connexion à la Base MongoDB
            ConnexionMongoDB cnx = new ConnexionMongoDB();
            MongoDatabase db = cnx.getDB("185.31.40.41", "27017", "m2icdi", "mdp12345", "m2icdi_cine");

            // Création d'une collection
            String lsCollection = psTable;
            /*
             Il faut tester l'existence de la collection
             */
            if (!collectionExiste(db, lsCollection)) {
                db.createCollection(lsCollection);
            }
            MongoCollection collection = db.getCollection(lsCollection);

            // CNX serveur et BD MySQL
            Connection lcn = DriverManager.getConnection("jdbc:mysql://mysql-m2iformationcdidl.alwaysdata.net:3306/m2iformationcdidl_cine", "147389", "mdp123");
            Statement lst = lcn.createStatement();

            ResultSet lrs = lst.executeQuery("SELECT * FROM " + psTable);
            ResultSetMetaData lrsmd = lrs.getMetaData();
//            DatabaseMetaData ldbmd = lcn.getMetaData();
//            String tTypes[] = null;
//            ResultSet lrsTables = ldbmd.getTables("m2iformationcdidl_cine", "", "", tTypes);
//            while(lrsTables.next()) {
//                lstTables.add(lrsTables.getString(3));
//            }
            int liCols = lrsmd.getColumnCount();

            while (lrs.next()) {
                // Création d'un document MDB
                Document doc = new Document();
                
                // valoriser le document avec les champs de la table
                for (int i = 0; i < liCols; i++) {
                    String name = lrsmd.getColumnName(i + 1);
                    doc.append(name, lrs.getString(i + 1));
                }
                
                // Ajout du document à la collection
                collection.insertOne(doc);
            }
            // DCNX serveur et BD MDB
            cnx.seDeconnecter();

            // DCNX serveur et BD MySQL
            lcn.close();
            lrs.close();

        } catch (SQLException ex) {
            System.out.println("SQL Error : " + ex.getMessage());
        }
    }
}
