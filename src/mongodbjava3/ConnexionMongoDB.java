/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava3;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author formation
 */
public class ConnexionMongoDB {
    /**
     * 
     * @param psIP
     * @param psPort
     * @return 
     */
    public MongoClient seConnecter(String psIP, String psPort, String psUser, String psPwd) {

        MongoClientURI uri = new MongoClientURI("mongodb://" + psUser + ":"+ psPwd + "@" +psIP + ":" + psPort);
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;

    } /// seConnecter
    /**
     * 
     * @param psIP
     * @param psPort
     * @return 
     */
    public MongoClient seConnecter(String psIP, String psPort) {

        MongoClientURI uri = new MongoClientURI("mongodb://" + psIP + ":" + psPort);
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;

    } /// seConnecter

    
    public MongoDatabase getDB(String psIP, String psPort, String psUser, String psPwd, String psDB) {
        MongoDatabase db = null;
        try{
            seConnecter(psIP, psPort, psUser, psPwd);
        }catch(Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        return db;
    }
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
