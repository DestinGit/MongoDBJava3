/*
 $m = new Mongo("mongodb://user:password@mongodb*.alwaysdata.com/database_name");
 var_dump($m);
 $db = $m->selectDB('database_name');
 $collection = new MongoCollection($db, 'collection_name');
 $person = array("name" => "Joe", "age" => 20);
 $collection->insert($person);
 $cursor = $collection->find();
 foreach ($cursor as $doc) {
 var_dump($doc);
 }

 BD : m2icdi_cine
 USER : m2icdi
 MDP : mdp12345

 */
package alwaysMongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class AlwaysDataConnexion {

    /*
     BD : m2icdi_cine
     USER : m2icdi
     MDP : mdp12345
     */
    public static void main(String[] args) {
        try {
            // Connexion a mongoDB
            // Connexion au serveur
            MongoClientURI uri = new MongoClientURI("mongodb://m2icdi:mdp12345@185.31.40.41/m2icdi_cine");
            MongoClient mongoClient = new MongoClient(uri);

            System.out.println(mongoClient);
            MongoDatabase bd = mongoClient.getDatabase("m2icdi_cine");
            System.out.println(bd);

            /*
             Creation d'une collection
             */
            bd.createCollection("films");
            // La deconnexion de la BD n'existe pas
            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("UnknownHostException : " + e.getMessage());
        }
    } /// main
} /// class AlwaysDataConnexion
