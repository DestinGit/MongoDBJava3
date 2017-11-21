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
public class ConnexionServeur {
     public static void main(String[] args) {
        try {
            // Connexion a mongoDB
            MongoClient mongoClient = new MongoClient("172.26.10.144", 27017);

            System.out.println(mongoClient);

            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("UnknownHostException : " + e.getMessage());
        }

    } /// main   
}
