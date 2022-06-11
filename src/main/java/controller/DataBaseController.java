package controller;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.ProcessModel;

public class DataBaseController {

    private final Gson gson = new Gson();
    Firestore fs;

    public DataBaseController() {
            gf();

    }

    public void gf() {
        try {

            //Tu ruta de la key firebase
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

    public void addData(String name, ArrayList<ProcessModel> pm, int size) {
        Map<String, Object> hm = new HashMap<String, Object>();
        try {
            for (int i = 0; i < size; i++) {
                System.out.println(i);
                hm.put("processName", pm.get(i).getProcessName());
                hm.put("id", i + 1);
                hm.put("capture", new Date().toString());
//                CollectionReference docR = fs.collection(name).document(new Date().toString()).collection(i + 1 + pm.get(i).getProcessName());
//                docR.document(pm.get(i).getProcessName()).set(hm);
                DocumentReference doc = fs.collection(name).document(pm.get(i).getProcessName()+"-"+new Date().getTime());
                          doc.set(hm);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createLocalJson(String name, ArrayList<ProcessModel> pm) {
        FileController fc = new FileController();
        fc.createFolder(name);
        pm.forEach(e -> {
            String json = gson.toJson(new BuildObject(e.getProcessName()));
            fc.createFile(e.getProcessName(), json);
        });

    }

}

class BuildObject {

    private final String description;

    public BuildObject(String description) {
        this.description = description;
    }

}
