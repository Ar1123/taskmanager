package controller;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProcessModel;

public class DataBaseController {

    private final Gson gson = new Gson();
    Firestore fs;

    public DataBaseController() {
    }

    public void gf() {
        try {

            FileInputStream serviceAccount
                    = new FileInputStream("C://Users//Laptop Asus M415DA//Desktop//Universidad//semestre 8//S.O//taller procesos//desktop//firebase.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            fs = FirestoreClient.getFirestore();

        } catch (IOException e) {
        }
    }

    public void addData(String name, ArrayList<ProcessModel> pm) {
        gf();
        Map<String, String> hm = new HashMap<String, String>();

        for (int i = 0; i < pm.size(); i++) {
            System.out.println(pm.get(i).getDescription());
            DocumentReference doc = fs.collection(name).document(pm.get(i).getName());
            hm.put("description", pm.get(i).getDescription());
            hm.put("id", (i + 1)+"");

            ApiFuture<WriteResult> result = doc.set(hm);
            try {
                System.out.println(result.get().getUpdateTime());
            } catch (InterruptedException ex) {
                System.out.println("------------------------");
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                System.out.println("***********************************");
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
     
    public void createLocalJson(String name, ArrayList<ProcessModel> pm) {
        FileController fc = new FileController();
        fc.createFolder(name);
        pm.forEach(e -> {
            String json = gson.toJson(new BuildObject(e.getDescription()));
            fc.createFile(e.getName(), json);
        });

    }

}

class BuildObject {

    private final String description;

    public BuildObject(String description) {
        this.description = description;
    }

}
