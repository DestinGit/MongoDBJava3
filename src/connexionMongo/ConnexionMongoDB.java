/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionMongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author formation
 */
public class ConnexionMongoDB {
    private MongoClient mongoClient;
    
    /**
     * 
     * @param psIP
     * @param psPort
     * @return 
     */
    public MongoClient seConnecter(String psIP, String psPort, String psUser, String psPwd, String psDB) {

        MongoClientURI uri = new MongoClientURI("mongodb://" + psUser + ":"+ psPwd + "@" +psIP + ":" + psPort + "/" + psDB);
        MongoClient mongoClient = new MongoClient(uri);
        //this.mongoClient = mongoClient;
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
        //this.mongoClient = mongoClient;
        return mongoClient;

    } /// seConnecter

    
    public MongoDatabase getDB(String psIP, String psPort, String psUser, String psPwd, String psDB) {
        MongoDatabase db = null;
        try{
            MongoClient mongoClient = seConnecter(psIP, psPort, psUser, psPwd, psDB);
            db = mongoClient.getDatabase(psDB);
            this.mongoClient = mongoClient;
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
    
    /**
     * 
     * @return 
     */
    public boolean seDeconnecter() {
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
