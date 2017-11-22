/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author formation
 */
public class ConnexionMongoDBTest {
    public static void main(String[] args) {
        ConnexionMongoDB cnx = new ConnexionMongoDB();
        
//        MongoClient mongoClient = cnx.seConnecter("172.26.10.144", "27017");
//        System.out.println(mongoClient);
//        
//        boolean lbOk = cnx.seDeconnecter(mongoClient);
//        System.out.println(lbOk);
//        
//        System.out.println(mongoClient);
        
        MongoDatabase db = cnx.getDB("185.31.40.41", "27017", "m2icdi", "mdp12345", "m2icdi_cine");
        System.out.println(db);
        System.out.println("db.listCollectionNames()");
    }      
}
