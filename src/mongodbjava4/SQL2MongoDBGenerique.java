/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava4;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import org.bson.Document;
import theconnections.TheConnections;

/**
 *
 * @author Administrateur
 */
public class SQL2MongoDBGenerique {

    public static void main(String[] args) {

        // CNX serveur et BD MySQL
        //Connection lcn = DriverManager.getConnection("jdbc:mysql://mysql-m2iformationcdidl.alwaysdata.net:3306/m2iformationcdidl_cine", "147389", "mdp123");
        Connection lcn = TheConnections.getConnexionMySQL();
        // CNX serveur et BD MDB
//            MongoClientURI uri = new MongoClientURI("mongodb://m2icdi:mdp12345@185.31.40.41:27017/m2icdi_cine");
//            MongoClient mongoClient = new MongoClient(uri);
        MongoClient mongoClient = TheConnections.getConnexionMongoDB();
        // sql2MongoDB(Connection pcnxSQL, MongoClient pcnxMD, String psBDSQL, String psBDMongoDB, String psTable, String psCollection)
        sql2MongoDB(lcn, mongoClient, "cinescope2017", "cinescope", "cinemas_parisiens", "cinemas");

        TheConnections.deconnexionSQL(lcn);
        TheConnections.deconnexionMDB(mongoClient);
        
    } /// main

    /**
     *
     * @param pcnxSQL
     * @param pcnxMDB
     * @param psBDSQL
     * @param psBDMongoDB
     * @param psTable
     * @param psCollection
     */
    public static void sql2MongoDB(Connection pcnxSQL, MongoClient pcnxMDB, String psBDSQL, String psBDMongoDB, String psTable, String psCollection) {
        try {

            MongoDatabase db = pcnxMDB.getDatabase(psBDMongoDB);
            // Création d'une collection
            String lsCollection = psCollection;
            /*
             Il faut tester l'existence de la collection
             */
//            if (!collectionExiste(db, lsCollection)) {
//                db.createCollection(lsCollection);
//            }
            MongoCollection collection = db.getCollection(lsCollection);

            // Exécution de la requête SQL et création du curseur
            String lsSelect = "SELECT * FROM " + psTable;
            Statement lstSQL = pcnxSQL.createStatement();
            ResultSet lrs = lstSQL.executeQuery(lsSelect);
            ResultSetMetaData lrsmd = lrs.getMetaData();
            int liCount = lrsmd.getColumnCount();
            String[] tCols = new String[liCount];
            for (int i = 1; i <= liCount; i++) {
                tCols[i - 1] = lrsmd.getColumnName(i);
            }
            Document doc;
            // Boucle dans le curseur
            while (lrs.next()) {
                // Création d'un document MDB
                doc = new Document();
//                doc.append("codeGenre", lrs.getString("code_genre"));
//                doc.append("libelleGenre", lrs.getString("libelle_genre"));
                for (int i = 1; i <= liCount; i++) {
                    doc.append(tCols[i - 1], lrs.getString(i));
                }
                // Ajout du document à la collection
                collection.insertOne(doc);
            }

            // Fermetures MySQL
            lrs.close();
            lstSQL.close();

            System.out.println("C'est fini !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } /// sql2MongoDB

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

} /// SQL2MongoDBGenerique
