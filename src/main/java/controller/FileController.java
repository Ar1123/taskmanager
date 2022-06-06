package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileController {
        //Tu ruta
    private final String urlBase = "C://Users//Laptop Asus M415DA//Desktop//Universidad//semestre 8//S.O//taller procesos//files";
    private String urlFinal;

    public FileController() {
    }

    public void createFolder(String nameFolder) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        File directory = new File(urlBase + "//" + nameFolder + "//" + dtf.format(LocalDateTime.now()).replace(":", "").replace("/", ""));
        urlFinal = urlBase + "//" + nameFolder + "//" + dtf.format(LocalDateTime.now()).replace(":", "").replace("/", "");
        directory.mkdirs();
    }

    public void createFile(String nameFile, String body) {

        try {
            try (FileWriter file = new FileWriter(urlFinal + "//" + nameFile + ".json")) {
                file.write(body);
                file.flush();
            }
        } catch (IOException e) {

            System.out.println(e);
        } finally {
        }
    }

}
