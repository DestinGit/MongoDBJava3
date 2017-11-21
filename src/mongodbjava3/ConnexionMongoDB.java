/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava3;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 *
 * @author formation
 */
public class ConnexionMongoDB {
    public MongoClient seConnecter(String psIP, String psPort) {

        MongoClientURI uri = new MongoClientURI("mongodb://" + psIP + ":" + psPort);
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;

    } /// seConnecter

    /**
     *
     * @param mongoClient
     * @return
     */
    public boolean seDeconnecter(MongoClient mongoClient) {
        boolean lb0K = true;

        try {
            if (mongoClient != null) {
                mongoClient.close();
            }
        } catch (Exception e) {
            lb0K = false;
        }

        return lb0K;
    } /// seDeconnecter    
}
