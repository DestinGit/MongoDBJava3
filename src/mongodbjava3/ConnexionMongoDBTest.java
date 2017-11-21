/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava3;

import com.mongodb.MongoClient;

/**
 *
 * @author formation
 */
public class ConnexionMongoDBTest {
    public static void main(String[] args) {
        ConnexionMongoDB cnx = new ConnexionMongoDB();
        
        MongoClient mongoClient = cnx.seConnecter("172.26.10.144", "27017");
        System.out.println(mongoClient);
        
        boolean lbOk = cnx.seDeconnecter(mongoClient);
        System.out.println(lbOk);
        
        System.out.println(mongoClient);
    }      
}
